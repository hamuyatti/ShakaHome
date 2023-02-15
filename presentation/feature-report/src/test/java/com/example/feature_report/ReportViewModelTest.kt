package com.example.feature_report

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.feature_report.uiState.NowStreamingInfoState
import com.example.feature_report.uiState.PastVideosInfoState
import com.example.feature_report.uiState.ReportScreenUiState
import com.example.usecase.FetchNowStreamingInfoUseCase
import com.example.usecase.FetchPastVideosUseCase
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ReportViewModelTest {
    @MockK
    private lateinit var fetchNowStreamingInfoUseCase: FetchNowStreamingInfoUseCase

    @MockK
    private lateinit var fetchPastVideosUseCase: FetchPastVideosUseCase

    private lateinit var viewModel: ReportViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = ReportViewModel(
            fetchNowStreamingInfoUseCase = fetchNowStreamingInfoUseCase,
            fetchPastVideosUseCase = fetchPastVideosUseCase
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun init_viewModel() = runTest {
        advanceUntilIdle()
        Truth.assertThat(viewModel.feedState.value).isEqualTo(
            ReportScreenUiState(
                nowStreamingInfoState = NowStreamingInfoState.Empty,
                pastVideosInfoState = PastVideosInfoState.Empty,
                false
            )
        )
    }

    @Test
    fun getFeedState() {
        Truth.assertThat("viewModel.feedState.value").isEqualTo("")
    }

    @Test
    fun onSwipeRefresh() {
    }
}