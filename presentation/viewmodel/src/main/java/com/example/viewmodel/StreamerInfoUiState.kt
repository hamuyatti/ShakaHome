package com.example.viewmodel

import com.example.model.StreamerBaseInfo

sealed interface StreamerInfoUiState{
    object Empty : StreamerInfoUiState
    object Loading : StreamerInfoUiState
    data class Success(val streamerBaseInfo: StreamerBaseInfo) : StreamerInfoUiState
    data class Error(val throwable: Throwable) : StreamerInfoUiState
}


