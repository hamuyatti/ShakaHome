package com.example.feature_report

import com.example.entity.PastVideosInfo

interface PastVideosInfoState {
    object Empty : PastVideosInfoState
    object Loading : PastVideosInfoState
    data class Success(val pastVideosState: PastVideosInfo) : PastVideosInfoState
    data class Error(val throwable: Throwable) : PastVideosInfoState
}