package com.example.viewmodel

import com.example.model.domain.NowStreamingInfo

sealed interface NowStreamingInfoUiState{
    object Empty : NowStreamingInfoUiState
    object Loading : NowStreamingInfoUiState
    data class Success(val nowStreamingInfo: NowStreamingInfo) : NowStreamingInfoUiState
    data class Error(val throwable: Throwable) : NowStreamingInfoUiState
}