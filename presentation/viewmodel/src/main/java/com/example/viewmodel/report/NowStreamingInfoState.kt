package com.example.viewmodel.report

import com.example.entity.NowStreamingInfo

sealed interface NowStreamingInfoState{
    object Empty : NowStreamingInfoState
    object Loading : NowStreamingInfoState
    data class Success(val nowStreamingInfo: com.example.entity.NowStreamingInfo) : NowStreamingInfoState
    data class Error(val throwable: Throwable) : NowStreamingInfoState
}