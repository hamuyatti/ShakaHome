package com.example.feature_streaming.uiState

import com.example.entity.NowStreamingInfo

sealed interface NowStreamingInfoState{
    object Empty : NowStreamingInfoState
    object Loading : NowStreamingInfoState
    data class Success(val nowStreamingInfo: NowStreamingInfo) :
        NowStreamingInfoState
    data class Error(val throwable: Throwable) : NowStreamingInfoState
}