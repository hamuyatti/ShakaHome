package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.FetchNowStreamingInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val useCase: FetchNowStreamingInfoUseCase
) : ViewModel() {

    private val _nowStreamingInfoUiState: MutableStateFlow<NowStreamingInfoState> =
        MutableStateFlow(NowStreamingInfoState.Empty)
    val nowStreamingInfoUiState = _nowStreamingInfoUiState.asStateFlow()

    private val _pastVideoInfoState: MutableStateFlow<PastVideosInfoState> =
        MutableStateFlow(PastVideosInfoState.Empty)
    val pastVideosInfoState = _pastVideoInfoState.asStateFlow()

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        fetch()
    }

    private fun fetch() {
        _isRefreshing.update { true }
        viewModelScope.launch {
            useCase()
            _nowStreamingInfoUiState.update { NowStreamingInfoState.Loading }
            runCatching {
                useCase()
            }.onSuccess { info ->
                _isRefreshing.update { false }
                info.nowStreamingInfo?.also { streamInfo ->
                    _nowStreamingInfoUiState.update { NowStreamingInfoState.Success(streamInfo) }
                } ?: _nowStreamingInfoUiState.update { NowStreamingInfoState.Empty }

                _pastVideoInfoState.update { PastVideosInfoState.Success(info.pastVideosInfo) }

            }.onFailure { e ->
                _isRefreshing.update { false }
                _nowStreamingInfoUiState.update { NowStreamingInfoState.Error(e) }
            }
        }
    }
}