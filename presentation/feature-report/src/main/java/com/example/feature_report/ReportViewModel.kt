package com.example.feature_report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_report.uiState.NowStreamingInfoState
import com.example.feature_report.uiState.PastVideosInfoState
import com.example.feature_report.uiState.ReportScreenUiState
import com.example.usecase.FetchNowStreamingInfoUseCase
import com.example.usecase.FetchPastVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ReportViewModel @Inject constructor(
    private val fetchNowStreamingInfoUseCase: FetchNowStreamingInfoUseCase,
    private val fetchPastVideosUseCase: FetchPastVideosUseCase
) : ViewModel() {

    private val nowStreamingInfoUiState: MutableStateFlow<NowStreamingInfoState> =
        MutableStateFlow(NowStreamingInfoState.Empty)

    private val pastVideoInfoState: MutableStateFlow<PastVideosInfoState> =
        MutableStateFlow(PastVideosInfoState.Empty)

    val feedState: StateFlow<ReportScreenUiState> = combine(
        nowStreamingInfoUiState,
        pastVideoInfoState
    ) { nowStreamingInfoState, pastVideosInfoState ->
        flowOf(
            ReportScreenUiState(
                nowStreamingInfoState = nowStreamingInfoState,
                pastVideosInfoState = pastVideosInfoState,
                isRefreshing = nowStreamingInfoState is NowStreamingInfoState.Loading || pastVideosInfoState is PastVideosInfoState.Loading
            )
        )
    }.flatMapLatest {
        it
    }.stateIn(
        scope = viewModelScope,
        initialValue = ReportScreenUiState(),
        started = SharingStarted.WhileSubscribed()
    )

    init {
        fetch()
    }

    fun onSwipeRefresh() {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            fetchNowStreaming()
        }
        viewModelScope.launch {
            fetchPastVideos()
        }
    }

    private suspend fun fetchNowStreaming() {
        nowStreamingInfoUiState.update { NowStreamingInfoState.Loading }
        runCatching {
            fetchNowStreamingInfoUseCase()
        }.onSuccess { info ->

            info?.also { streamInfo ->
                nowStreamingInfoUiState.update { NowStreamingInfoState.Success(streamInfo) }
            } ?: nowStreamingInfoUiState.update { NowStreamingInfoState.Empty }

        }.onFailure { e ->
            nowStreamingInfoUiState.update { NowStreamingInfoState.Error(e) }
        }
    }

    private suspend fun fetchPastVideos() {
        pastVideoInfoState.update { PastVideosInfoState.Loading }
        runCatching {
            fetchPastVideosUseCase()
        }.onSuccess { info ->
            pastVideoInfoState.update { PastVideosInfoState.Success(info) }
        }.onFailure { e ->
            nowStreamingInfoUiState.update { NowStreamingInfoState.Error(e) }
        }

    }
}