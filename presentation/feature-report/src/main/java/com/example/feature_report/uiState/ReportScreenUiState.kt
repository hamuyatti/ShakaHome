package com.example.feature_report.uiState

import com.example.feature_report.uiState.NowStreamingInfoState
import com.example.feature_report.uiState.PastVideosInfoState

data class ReportScreenUiState(
    val nowStreamingInfoState: NowStreamingInfoState = NowStreamingInfoState.Loading,
    val pastVideosInfoState: PastVideosInfoState = PastVideosInfoState.Loading,
    val isRefreshing: Boolean = true
)
