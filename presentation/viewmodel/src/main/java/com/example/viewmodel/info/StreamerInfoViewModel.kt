package com.example.viewmodel.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.FetchFollowInfoUseCase
import com.example.usecase.FetchMoreFollowInfoUseCase
import com.example.usecase.FetchStreamerBaseInfoUseCase
import com.example.usecase.SortFollowListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StreamerInfoViewModel @Inject constructor(
    private val fetchBaseInfoUseCase: FetchStreamerBaseInfoUseCase,
    private val fetchFollowInfoUseCase: FetchFollowInfoUseCase,
    private val fetchMoreFollowInfoUseCase: FetchMoreFollowInfoUseCase,
    private val sortFollowListUseCase: SortFollowListUseCase
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
        if (uiState !is FollowInfoUiState.Success) return
        uiState.followInfo.cursor?.let {
            _followInfoUiState.update { FollowInfoUiState.MoreLoading(uiState.followInfo) }
            viewModelScope.launch {
                fetchMoreFollowInfo(it)
            }
        }
    }

    fun onToggled(isByNew: Boolean) {
        val uiState = _followInfoUiState.value
        if (uiState !is FollowInfoUiState.Success) return
        _followInfoUiState.update { FollowInfoUiState.Loading }
        if (uiState.followInfo.hasCursor) {
            // まだ取得しきれてない内容がある場合fetchしてからソートをかける。
            // 今のところのフォロー総数がfetch二回分で取得できるからこの処理の仕方でできるけど、増えていったら変えないとなぁ
            _followInfoUiState.update { FollowInfoUiState.MoreLoading(uiState.followInfo) }
            viewModelScope.launch {
                fetchMoreFollowInfoWithSort(
                    uiState.followInfo.cursor ?: return@launch,
                    isByNew = isByNew
                )
            }
        } else {
            viewModelScope.launch {
                _followInfoUiState.update {
                    FollowInfoUiState.Success(
                        sortFollowListUseCase(followInfo = uiState.followInfo, isByNew = isByNew)
                    )
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
            fetchBaseInfoUseCase()
        }.onSuccess { info ->
            _baseInfoUiState.update { StreamerBaseInfoUiState.Success(info) }
        }.onFailure { error ->
            _baseInfoUiState.update { StreamerBaseInfoUiState.Error(error) }
        }
    }

    private suspend fun fetchFollowInfo() {
        _followInfoUiState.update { FollowInfoUiState.Loading }
        runCatching {
            fetchFollowInfoUseCase()
        }.onSuccess { info ->
            _followInfoUiState.update { FollowInfoUiState.Success(info) }
        }.onFailure { error ->
            _followInfoUiState.update { FollowInfoUiState.Error(error) }
        }
    }

    private suspend fun fetchMoreFollowInfo(nextCursor: String) {
        runCatching {
            fetchMoreFollowInfoUseCase(nextCursor)
        }.onSuccess { info ->
            _followInfoUiState.update { FollowInfoUiState.Success(info) }
        }.onFailure { error ->
            _followInfoUiState.update { FollowInfoUiState.Error(error) }
        }
    }

    private suspend fun fetchMoreFollowInfoWithSort(nextCursor: String, isByNew: Boolean) {
        runCatching {
            fetchMoreFollowInfoUseCase(nextCursor)
        }.onSuccess { info ->
            _followInfoUiState.update {
                FollowInfoUiState.Success(
                    sortFollowListUseCase(
                        followInfo = info,
                        isByNew = isByNew
                    )
                )
            }
        }.onFailure { error ->
            _followInfoUiState.update { FollowInfoUiState.Error(error) }
        }
    }

}