package com.example.feature_streaming

import com.example.entity.NowStreamingInfo
import com.example.entity.PastVideosInfo
import com.example.feature_streaming.uiState.NowStreamingInfoState
import com.example.feature_streaming.uiState.PastVideosInfoState
import com.example.feature_streaming.uiState.ReportScreenUiState
import com.example.usecase.FetchNowStreamingInfoUseCase
import com.example.usecase.FetchPastVideosUseCase
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class StreamingViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var fetchNowStreamingInfoUseCase: FetchNowStreamingInfoUseCase

    @MockK
    private lateinit var fetchPastVideosUseCase: FetchPastVideosUseCase

    @MockK
    private lateinit var nowStreamingInfo: NowStreamingInfo

    @MockK
    private lateinit var pastVideosState: PastVideosInfo

    private lateinit var viewModel: StreamingViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = StreamingViewModel(
            fetchNowStreamingInfoUseCase = fetchNowStreamingInfoUseCase,
            fetchPastVideosUseCase = fetchPastVideosUseCase,
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun init_viewModel_success() = runTest(UnconfinedTestDispatcher()) {
        coEvery { fetchNowStreamingInfoUseCase() } returns nowStreamingInfo
        coEvery { fetchPastVideosUseCase() } returns pastVideosState

        val collectJob = launch() {
            viewModel.feedState.collect()
        }
        Truth.assertThat(viewModel.feedState.value).isEqualTo(
            ReportScreenUiState(
                nowStreamingInfoState = NowStreamingInfoState.Empty,
                pastVideosInfoState = PastVideosInfoState.Empty,
                isRefreshing = false
            )
        )

        viewModel.init()

        Truth.assertThat(viewModel.feedState.value).isEqualTo(
            ReportScreenUiState(
                nowStreamingInfoState = NowStreamingInfoState.Success(nowStreamingInfo),
                pastVideosInfoState = PastVideosInfoState.Success(pastVideosState),
                false
            )
        )
        // cancelしないとテストが終わらなかった　→　StateFlowのcollectが終わっていない！ドキュメント通りだね！！
        collectJob.cancel()
    }

    @Test
    fun init_test_failed() = runTest(UnconfinedTestDispatcher()) {
        val exception1 = Throwable()
        val exception2 = Throwable()
        coEvery { fetchNowStreamingInfoUseCase() } throws exception1
        coEvery { fetchPastVideosUseCase() } throws exception2

        val collectJob = launch {
            viewModel.feedState.collect()
        }

        Truth.assertThat(viewModel.feedState.value).isEqualTo(
            ReportScreenUiState(
                nowStreamingInfoState = NowStreamingInfoState.Empty,
                pastVideosInfoState = PastVideosInfoState.Empty,
                isRefreshing = false
            )
        )

        viewModel.init()

        Truth.assertThat(viewModel.feedState.value).isEqualTo(
            ReportScreenUiState(
                nowStreamingInfoState = NowStreamingInfoState.Error(exception1),
                pastVideosInfoState = PastVideosInfoState.Error(exception2),
                false
            )
        )
        collectJob.cancel()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule constructor(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}
