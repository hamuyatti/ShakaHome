package com.example.viewmodel.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.FetchFollowInfoUseCase
import com.example.usecase.FetchMoreFollowInfoUseCase
import com.example.usecase.FetchStreamerBaseInfoUseCase
import com.example.usecase.SortFollowListBySomeoneNewUseCase
import com.example.usecase.SortFollowListBySomeoneOldUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamerInfoViewModel @Inject constructor(
    private val baseInfoUseCase: FetchStreamerBaseInfoUseCase,
    private val followInfoUseCase: FetchFollowInfoUseCase,
    private val moreFollowInfoUseCase: FetchMoreFollowInfoUseCase,
    private val sortFollowListBySomeoneNewUseCase: SortFollowListBySomeoneNewUseCase,
    private val sortFollowListBySomeoneOldUseCase: SortFollowListBySomeoneOldUseCase
) : ViewModel() {

    private val _baseInfoUiState: MutableStateFlow<StreamerBaseInfoUiState> =
        MutableStateFlow(StreamerBaseInfoUiState.Empty)
    val baseInfoUiState = _baseInfoUiState.asStateFlow()

    private val _followInfoUiState: MutableStateFlow<FollowInfoUiState> =
        MutableStateFlow(FollowInfoUiState.Empty)
    val followInfoUiState = _followInfoUiState.asStateFlow()

    private val _isRefreshing: MutableStateFlow<Boolean> =
        MutableStateFlow(_followInfoUiState.value is FollowInfoUiState.Loading && _baseInfoUiState.value is StreamerBaseInfoUiState.Loading)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        fetch()
    }

    fun onSwipeRefresh() {
        fetch()
    }

    fun onReachBottom() {
        val uiState = _followInfoUiState.value
        if (uiState is FollowInfoUiState.Success) {
            uiState.followInfo.cursor?.let {
                _followInfoUiState.update { FollowInfoUiState.MoreLoading(uiState.followInfo) }
                viewModelScope.launch {
                    fetchMoreFollowInfo(it)
                }
            }
        }
    }

    private fun fetch() {
        viewModelScope.launch {
            fetchBaseInfo()
            fetchFollowInfo()
        }
    }

    private suspend fun fetchBaseInfo() {
        _baseInfoUiState.update { StreamerBaseInfoUiState.Loading }
        runCatching {
            baseInfoUseCase()
        }.onSuccess { info ->
            _baseInfoUiState.update { StreamerBaseInfoUiState.Success(info) }
        }.onFailure { error ->
            _baseInfoUiState.update { StreamerBaseInfoUiState.Error(error) }
        }
    }

    private suspend fun fetchFollowInfo() {
        _followInfoUiState.update { FollowInfoUiState.Loading }
        followInfoUseCase()
        runCatching {
            followInfoUseCase()
        }.onSuccess { info ->
            _followInfoUiState.update { FollowInfoUiState.Success(info) }
        }.onFailure { error ->
            _followInfoUiState.update { FollowInfoUiState.Error(error) }
        }
    }

    private suspend fun fetchMoreFollowInfo(nextCursor: String) {
        runCatching {
            moreFollowInfoUseCase(nextCursor)
        }.onSuccess { info ->
            _followInfoUiState.update { FollowInfoUiState.Success(info) }
        }.onFailure { error ->
            _followInfoUiState.update { FollowInfoUiState.Error(error) }
        }
    }

}