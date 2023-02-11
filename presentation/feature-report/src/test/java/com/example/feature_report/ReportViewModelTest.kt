package com.example.feature_report

import com.example.usecase.FetchNowStreamingInfoUseCase
import com.example.usecase.FetchPastVideosUseCase
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
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

    }

    @Test
    fun getFeedState() {
    }

    @Test
    fun onSwipeRefresh() {
    }
}