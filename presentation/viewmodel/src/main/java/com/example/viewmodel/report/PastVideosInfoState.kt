package com.example.viewmodel.report

import com.example.model.domain.PastVideosInfo

interface PastVideosInfoState {
    object Empty : PastVideosInfoState
    object Loading : PastVideosInfoState
    data class Success(val pastVideosState: PastVideosInfo) : PastVideosInfoState
    data class Error(val throwable: Throwable) : PastVideosInfoState
}