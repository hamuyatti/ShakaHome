package com.example.viewmodel.info

data class InfoScreenUiState(
    val followInfoState: FollowInfoUiState = FollowInfoUiState.Loading,
    val streamerBaseInfoState: StreamerBaseInfoUiState = StreamerBaseInfoUiState.Loading,
    val isRefreshing: Boolean = true
)
