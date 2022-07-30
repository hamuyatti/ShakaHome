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

    private val _nowStreamingInfoUiState: MutableStateFlow<NowStreamingInfoUiState> =
        MutableStateFlow(NowStreamingInfoUiState.Empty)
    val nowStreamingInfoUiState = _nowStreamingInfoUiState.asStateFlow()

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        refresh()
    }

    fun refresh(){
        fetchNowStreamingInfo()
    }

    private fun fetchNowStreamingInfo() {
        _isRefreshing.update { true }
        viewModelScope.launch {
            useCase()
            _nowStreamingInfoUiState.update { NowStreamingInfoUiState.Loading }
            runCatching {
                useCase()
            }.onSuccess { info ->
                _isRefreshing.update { false }
                if (info == null) {
                    _nowStreamingInfoUiState.update { NowStreamingInfoUiState.Empty }
                } else {
                    _nowStreamingInfoUiState.update { NowStreamingInfoUiState.Success(info) }
                }
            }.onFailure { e ->
                _isRefreshing.update { false }
                _nowStreamingInfoUiState.update { NowStreamingInfoUiState.Error(e) }
            }
        }
    }
}