package com.example.viewmodel.info

import com.example.entity.StreamerBaseInfo

sealed interface StreamerBaseInfoUiState {
    object Empty : StreamerBaseInfoUiState
    object Loading : StreamerBaseInfoUiState
    data class Success(val baseInfo: StreamerBaseInfo) : StreamerBaseInfoUiState
    data class Error(val throwable: Throwable) : StreamerBaseInfoUiState
}


