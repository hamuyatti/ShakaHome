package com.example.viewmodel

import com.example.model.domain.NowStreamingInfo

sealed interface NowStreamingInfoState{
    object Empty : NowStreamingInfoState
    object Loading : NowStreamingInfoState
    data class Success(val nowStreamingInfo: NowStreamingInfo) : NowStreamingInfoState
    data class Error(val throwable: Throwable) : NowStreamingInfoState
}