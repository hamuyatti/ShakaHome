package com.example.feature_report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.FetchNowStreamingInfoUseCase
import com.example.usecase.FetchPastVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val fetchNowStreamingInfoUseCase: FetchNowStreamingInfoUseCase,
    private val fetchPastVideosUseCase: FetchPastVideosUseCase
) : ViewModel() {

    private val _nowStreamingInfoUiState: MutableStateFlow<com.example.feature_report.NowStreamingInfoState> =
        MutableStateFlow(com.example.feature_report.NowStreamingInfoState.Empty)
    val nowStreamingInfoUiState = _nowStreamingInfoUiState.asStateFlow()

    private val _pastVideoInfoState: MutableStateFlow<com.example.feature_report.PastVideosInfoState> =
        MutableStateFlow(com.example.feature_report.PastVideosInfoState.Empty)
    val pastVideosInfoState = _pastVideoInfoState.asStateFlow()

    private val _isRefreshing: MutableStateFlow<Boolean> =
        MutableStateFlow(_nowStreamingInfoUiState.value is com.example.feature_report.NowStreamingInfoState.Loading && _pastVideoInfoState.value is com.example.feature_report.PastVideosInfoState.Loading)
    val isRefreshing = _isRefreshing.asStateFlow()

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
        _nowStreamingInfoUiState.update { com.example.feature_report.NowStreamingInfoState.Loading }
        runCatching {
            fetchNowStreamingInfoUseCase()
        }.onSuccess { info ->

            info?.also { streamInfo ->
                _nowStreamingInfoUiState.update { com.example.feature_report.NowStreamingInfoState.Success(streamInfo) }
            } ?: _nowStreamingInfoUiState.update { com.example.feature_report.NowStreamingInfoState.Empty }

        }.onFailure { e ->
            _nowStreamingInfoUiState.update { com.example.feature_report.NowStreamingInfoState.Error(e) }
        }
    }

    private suspend fun fetchPastVideos() {
        _pastVideoInfoState.update { com.example.feature_report.PastVideosInfoState.Loading }
        runCatching {
            fetchPastVideosUseCase()
        }.onSuccess { info ->
            _pastVideoInfoState.update { com.example.feature_report.PastVideosInfoState.Success(info) }
        }.onFailure { e ->
            _nowStreamingInfoUiState.update { com.example.feature_report.NowStreamingInfoState.Error(e) }
        }

    }
}