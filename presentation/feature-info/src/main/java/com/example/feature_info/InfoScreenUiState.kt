package com.example.feature_info

data class InfoScreenUiState(
    val followInfoState: com.example.feature_info.FollowInfoUiState = com.example.feature_info.FollowInfoUiState.Loading,
    val streamerBaseInfoState: StreamerBaseInfoUiState = StreamerBaseInfoUiState.Loading,
    val isRefreshing: Boolean = true
)
