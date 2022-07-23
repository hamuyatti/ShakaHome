package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.domain.NowStreamingInfo
import com.example.usecase.FetchNowStreamingInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val useCase: FetchNowStreamingInfoUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ReportUiState> =
        MutableStateFlow(ReportUiState.Empty)
    val uiState = _uiState.asStateFlow()

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        fetchNowStreamingInfo()
    }

    fun fetchNowStreamingInfo() {
        _isRefreshing.update { true }
        viewModelScope.launch {
            _uiState.update { ReportUiState.Loading }
            runCatching {
                useCase()
            }.onSuccess { info ->
                _isRefreshing.update { false }
                if (info == null) {
                    _uiState.update { ReportUiState.Empty }
                } else {
                    _uiState.update { ReportUiState.Success(info) }
                }
            }.onFailure { e ->
                _isRefreshing.update { false }
                _uiState.update { ReportUiState.Error(e) }
            }
        }
    }
}