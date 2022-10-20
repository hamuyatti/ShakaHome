package com.example.viewmodel.info

import com.example.model.domain.FollowInfo

sealed interface FollowInfoUiState {
    object Empty : FollowInfoUiState
    object Loading : FollowInfoUiState
    data class Success(val followInfo: FollowInfo) : FollowInfoUiState
    data class Error(val throwable: Throwable) : FollowInfoUiState
    data class MoreLoading(val followInfo: FollowInfo) : FollowInfoUiState
}