package com.example.feature_report

data class ReportScreenUiState(
    val nowStreamingInfoState: NowStreamingInfoState = NowStreamingInfoState.Loading,
    val pastVideosInfoState: PastVideosInfoState = PastVideosInfoState.Loading,
    val isRefreshing: Boolean = true
)
