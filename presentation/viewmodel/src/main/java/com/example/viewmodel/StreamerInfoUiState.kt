package com.example.viewmodel

import com.example.model.StreamerInfo

sealed interface StreamerInfoUiState{
    object Empty : StreamerInfoUiState
    object IsLoading : StreamerInfoUiState
    data class Success(val streamerInfo: StreamerInfo) : StreamerInfoUiState
    data class Error(val throwable: Throwable) : StreamerInfoUiState
}


