package com.example.viewmodel

import com.example.model.domain.StreamerInfo

sealed interface StreamerInfoUiState{
    object Empty : StreamerInfoUiState
    object Loading : StreamerInfoUiState
    data class Success(val streamerInfo: StreamerInfo) : StreamerInfoUiState
    data class Error(val throwable: Throwable) : StreamerInfoUiState
}


