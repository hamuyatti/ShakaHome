package com.example.viewmodel

import com.example.model.domain.NowStreamingInfo

sealed interface ReportUiState{
    object Empty : ReportUiState
    object Loading : ReportUiState
    data class Success(val streamerInfo: NowStreamingInfo) : ReportUiState
    data class Error(val throwable: Throwable) : ReportUiState
}