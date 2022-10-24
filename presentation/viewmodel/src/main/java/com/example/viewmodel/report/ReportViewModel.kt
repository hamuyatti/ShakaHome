package com.example.viewmodel.report

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

    private val _nowStreamingInfoUiState: MutableStateFlow<NowStreamingInfoState> =
        MutableStateFlow(NowStreamingInfoState.Empty)
    val nowStreamingInfoUiState = _nowStreamingInfoUiState.asStateFlow()

    private val _pastVideoInfoState: MutableStateFlow<PastVideosInfoState> =
        MutableStateFlow(PastVideosInfoState.Empty)
    val pastVideosInfoState = _pastVideoInfoState.asStateFlow()

    private val _isRefreshing: MutableStateFlow<Boolean> =
        MutableStateFlow(_nowStreamingInfoUiState.value is NowStreamingInfoState.Loading && _pastVideoInfoState.value is PastVideosInfoState.Loading)
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
            fetchPastVideos()
        }
    }

    private suspend fun fetchNowStreaming() {
        _nowStreamingInfoUiState.update { NowStreamingInfoState.Loading }
        runCatching {
            fetchNowStreamingInfoUseCase()
        }.onSuccess { info ->

            info?.also { streamInfo ->
                _nowStreamingInfoUiState.update { NowStreamingInfoState.Success(streamInfo) }
            } ?: _nowStreamingInfoUiState.update { NowStreamingInfoState.Empty }

        }.onFailure { e ->
            _nowStreamingInfoUiState.update { NowStreamingInfoState.Error(e) }
        }
    }

    private suspend fun fetchPastVideos() {
        _pastVideoInfoState.update { PastVideosInfoState.Loading }
        runCatching {
            fetchPastVideosUseCase()
        }.onSuccess { info ->
            _pastVideoInfoState.update { PastVideosInfoState.Success(info) }
        }.onFailure { e ->
            _nowStreamingInfoUiState.update { NowStreamingInfoState.Error(e) }
        }

    }
}