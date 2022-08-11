package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.domain.StreamerBaseInfo
import com.example.usecase.FetchFollowInfoUseCase
import com.example.usecase.FetchStreamerBaseInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamerInfoViewModel @Inject constructor(
    private val baseInfoUseCase: FetchStreamerBaseInfoUseCase,
    private val followInfoUseCase: FetchFollowInfoUseCase
) : ViewModel() {

    private val _baseInfoUiState: MutableStateFlow<StreamerBaseInfoUiState> =
        MutableStateFlow(StreamerBaseInfoUiState.Empty)
    val baseInfoUiState = _baseInfoUiState.asStateFlow()

    private val _followInfoUiState: MutableStateFlow<FollowInfoUiState> =
        MutableStateFlow(FollowInfoUiState.Empty)
    val followInfoUiState = _followInfoUiState.asStateFlow()

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        fetch()
    }

    fun refresh(){
        fetch()
    }

    private fun fetch(){
        viewModelScope.launch {
            fetchBaseInfo()
            fetchFollowInfo()
        }
    }

    private suspend fun fetchBaseInfo() {
        runCatching {
            baseInfoUseCase()
        }.onSuccess { info ->
            _baseInfoUiState.update { StreamerBaseInfoUiState.Success(info) }
        }.onFailure { error ->
            _baseInfoUiState.update { StreamerBaseInfoUiState.Error(error) }
        }
    }

    private suspend fun fetchFollowInfo() {
        kotlin.runCatching {
            followInfoUseCase()
        }.onSuccess { info ->
            _followInfoUiState.update { FollowInfoUiState.Success(info) }
        }.onFailure { error ->
            _followInfoUiState.update { FollowInfoUiState.Error(error) }
        }
    }

}