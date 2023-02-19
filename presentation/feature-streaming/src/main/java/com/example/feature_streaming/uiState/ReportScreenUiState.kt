package com.example.feature_streaming.uiState

data class ReportScreenUiState(
    val nowStreamingInfoState: NowStreamingInfoState = NowStreamingInfoState.Loading,
    val pastVideosInfoState: PastVideosInfoState = PastVideosInfoState.Loading,
    val isRefreshing: Boolean = true
)
