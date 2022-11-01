package com.example.feature_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.FetchFollowInfoUseCase
import com.example.usecase.FetchMoreFollowInfoUseCase
import com.example.usecase.FetchStreamerBaseInfoUseCase
import com.example.usecase.SortFollowListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class StreamerInfoViewModel @Inject constructor(
    private val fetchBaseInfoUseCase: FetchStreamerBaseInfoUseCase,
    private val fetchFollowInfoUseCase: FetchFollowInfoUseCase,
    private val fetchMoreFollowInfoUseCase: FetchMoreFollowInfoUseCase,
    private val sortFollowListUseCase: SortFollowListUseCase
) : ViewModel() {

    private val baseInfoUiState: MutableStateFlow<StreamerBaseInfoUiState> =
        MutableStateFlow(StreamerBaseInfoUiState.Empty)

    private val followInfoUiState: MutableStateFlow<FollowInfoUiState> =
        MutableStateFlow(FollowInfoUiState.Empty)

    val feedState: StateFlow<InfoScreenUiState> = combine(
        baseInfoUiState,
        followInfoUiState,
    ) { baseInfoUiState: StreamerBaseInfoUiState, followInfoUiState: FollowInfoUiState ->
        flowOf(InfoScreenUiState(
            streamerBaseInfoState = baseInfoUiState,
            followInfoState = followInfoUiState,
            isRefreshing = baseInfoUiState is StreamerBaseInfoUiState.Loading || followInfoUiState is FollowInfoUiState.Loading
        ))
    }.flatMapLatest {
        it
    }.stateIn(
        scope = viewModelScope,
        initialValue = InfoScreenUiState(),
        started = SharingStarted.WhileSubscribed()
    )

    init {
        fetch()
    }

    fun onSwipeRefresh() {
        fetch()
    }

    fun onReachBottom() {
        val uiState = followInfoUiState.value
        if (uiState !is FollowInfoUiState.Success) return
        uiState.followInfo.cursor?.let {
            followInfoUiState.update {
                FollowInfoUiState.MoreLoading(
                    uiState.followInfo
                )
            }
            viewModelScope.launch {
                fetchMoreFollowInfo(it)
            }
        }
    }

    fun onToggled(isByNew: Boolean) {
        val uiState = followInfoUiState.value
        if (uiState !is FollowInfoUiState.Success) return
        followInfoUiState.update { FollowInfoUiState.Loading }
        if (uiState.followInfo.hasCursor) {
            // まだ取得しきれてない内容がある場合fetchしてからソートをかける。
            // 今のところのフォロー総数がfetch二回分で取得できるからこの処理の仕方でできるけど、増えていったら変えないとなぁ
            followInfoUiState.update {
                FollowInfoUiState.MoreLoading(
                    uiState.followInfo
                )
            }
            viewModelScope.launch {
                fetchMoreFollowInfoWithSort(
                    uiState.followInfo.cursor ?: return@launch,
                    isByNew = isByNew
                )
            }
        } else {
            viewModelScope.launch {
                followInfoUiState.update {
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
        }
        viewModelScope.launch {
            fetchFollowInfo()
        }
    }

    private suspend fun fetchBaseInfo() {
        baseInfoUiState.update { StreamerBaseInfoUiState.Loading }
        runCatching {
            fetchBaseInfoUseCase()
        }.onSuccess { info ->

            info?.also { notNulInfo ->
                baseInfoUiState.update {
                    StreamerBaseInfoUiState.Success(
                        notNulInfo
                    )
                }
            } ?: baseInfoUiState.update { StreamerBaseInfoUiState.Empty }
        }.onFailure { error ->
            baseInfoUiState.update { StreamerBaseInfoUiState.Error(error) }
        }
    }

    private suspend fun fetchFollowInfo() {
        followInfoUiState.update { FollowInfoUiState.Loading }
        runCatching {
            fetchFollowInfoUseCase()
        }.onSuccess { info ->
            followInfoUiState.update { FollowInfoUiState.Success(info) }
        }.onFailure { error ->
            followInfoUiState.update { FollowInfoUiState.Error(error) }
        }
    }

    private suspend fun fetchMoreFollowInfo(nextCursor: String) {
        runCatching {
            fetchMoreFollowInfoUseCase(nextCursor)
        }.onSuccess { info ->
            followInfoUiState.update { FollowInfoUiState.Success(info) }
        }.onFailure { error ->
            followInfoUiState.update { FollowInfoUiState.Error(error) }
        }
    }

    private suspend fun fetchMoreFollowInfoWithSort(nextCursor: String, isByNew: Boolean) {
        runCatching {
            fetchMoreFollowInfoUseCase(nextCursor)
        }.onSuccess { info ->
            followInfoUiState.update {
                FollowInfoUiState.Success(
                    sortFollowListUseCase(
                        followInfo = info,
                        isByNew = isByNew
                    )
                )
            }
        }.onFailure { error ->
            followInfoUiState.update { FollowInfoUiState.Error(error) }
        }
    }

}