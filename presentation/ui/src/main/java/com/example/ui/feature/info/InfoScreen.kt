package com.example.ui.feature.info

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.Header
import com.example.compose.SimpleFab
import com.example.compose.ToggleSwitch
import com.example.model.CarouselModel
import com.example.model.domain.FollowInfo
import com.example.resource.R
import com.example.ui.ShakaHomeTopAppBar
import com.example.ui.utils.ImageCarousel
import com.example.viewmodel.info.FollowInfoUiState
import com.example.viewmodel.info.InfoScreenUiState
import com.example.viewmodel.info.StreamerBaseInfoUiState
import com.example.viewmodel.info.StreamerInfoViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ForInfoRoute(
    modifier: Modifier = Modifier,
    viewModel: StreamerInfoViewModel = hiltViewModel(),
    onSettingIconClick: () -> Unit
) {
    val feedState by viewModel.feedState.collectAsStateWithLifecycle()
    var toggleState by remember { mutableStateOf(true) }
    val listState = rememberLazyListState()

    // flow rowとかsticky header使ってからここの結果が変わっている　泣
    val isReachedBottom by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size == listState.layoutInfo.totalItemsCount
        }
    }

    val currentOnReachedBottom by rememberUpdatedState(viewModel::onReachBottom)
    LaunchedEffect(isReachedBottom) {
        snapshotFlow { isReachedBottom }
            .collect { isReached ->
                if (isReached) {
                    currentOnReachedBottom()
                }
            }
    }

    InfoScreen(
        modifier = modifier,
        feedState = feedState,
        toggleState = toggleState,
        listState = listState,
        isReachedBottom = isReachedBottom,
        onSettingIconClick = onSettingIconClick,
        onRefresh = viewModel::onSwipeRefresh,
        onToggled = {
            viewModel.onToggled(it)
            toggleState = it
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    feedState: InfoScreenUiState,
    isReachedBottom: Boolean,
    onSettingIconClick: () -> Unit,
    onToggled: (Boolean) -> Unit,
    toggleState: Boolean,
    listState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = feedState.isRefreshing),
        onRefresh = { onRefresh() },
        indicatorAlignment = Alignment.TopCenter,
        indicatorPadding = PaddingValues(100.dp),
        modifier = modifier
    ) {
        Scaffold(
            topBar = {
                ShakaHomeTopAppBar(
                    titleRes = R.string.app_name,
                    actionIcon = Icons.Outlined.Settings,
                    actionIconContentDescription = stringResource(
                        id = R.string.top_app_bar_action_button_content_desc
                    ),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                    ),
                    onActionClick = onSettingIconClick
                )
            },
            containerColor = Color.Transparent,
            floatingActionButton = {
                AnimatedVisibility(
                    visible = isReachedBottom,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    SimpleFab(
                        onClick = {
                            coroutineScope.launch {
                                listState.scrollToItem(0)
                            }
                        },
                        icon = Icons.Filled.ArrowUpward,
                        tint = Color.Yellow
                    )
                }
            }
        ) { innerPadding ->
            val context = LocalContext.current
            val halfScreenWidth = LocalConfiguration.current.screenWidthDp / 2
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(innerPadding)
            ) {
                BaseInfoFeed(
                    uiState = feedState.streamerBaseInfoState,
                    context = context,
                )
                FollowInfoFeed(
                    uiState = feedState.followInfoState,
                    context = context,
                    halfScreenWidth = halfScreenWidth,
                    onToggled = onToggled,
                    toggleState = toggleState
                )
            }
        }
    }
}

private fun LazyListScope.BaseInfoFeed(
    uiState: StreamerBaseInfoUiState,
    context: Context,
) {
    when (uiState) {
        is StreamerBaseInfoUiState.Loading -> {}

        is StreamerBaseInfoUiState.Error -> {
            Toast.makeText(context, R.string.error_notice, Toast.LENGTH_SHORT).show()
        }

        is StreamerBaseInfoUiState.Empty -> {}

        is StreamerBaseInfoUiState.Success -> {
            item {
                Text(
                    text = stringResource(id = R.string.name),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = uiState.baseInfo.displayName,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                uiState.baseInfo.let {
                    ImageCarousel(
                        info = listOf(
                            CarouselModel(
                                it.profileImageUrl,
                                stringResource(id = R.string.profile_image)
                            ),
                            CarouselModel(
                                it.offlineImageUrl,
                                stringResource(id = R.string.offline_image)
                            )
                        )
                    )
                }
            }
        }
    }
}

fun LazyListScope.FollowInfoFeed(
    uiState: FollowInfoUiState,
    context: Context,
    modifier: Modifier = Modifier,
    halfScreenWidth: Int,
    toggleState: Boolean,
    onToggled: (Boolean) -> Unit,
) {
    when (uiState) {
        FollowInfoUiState.Empty -> {}
        FollowInfoUiState.Loading -> {}
        is FollowInfoUiState.Error -> {
            Toast.makeText(context, R.string.error_notice, Toast.LENGTH_SHORT).show()
        }

        is FollowInfoUiState.Success -> {
            FollowContent(
                followInfo = uiState.followInfo,
                halfScreenWidth = halfScreenWidth,
                modifier = modifier,
                onToggled = onToggled,
                toggleState = toggleState
            )
        }

        is FollowInfoUiState.MoreLoading -> {
            FollowContent(
                followInfo = uiState.followInfo,
                halfScreenWidth,
                modifier = modifier,
                onToggled = onToggled,
                toggleState = toggleState
            )
            item {
                CircularProgressIndicator()
            }
        }
    }
}

private fun LazyListScope.FollowContent(
    followInfo: FollowInfo,
    halfScreenWidth: Int,
    onToggled: (Boolean) -> Unit,
    toggleState: Boolean,
    modifier: Modifier = Modifier
) {
    Header {
        Column(modifier = modifier.background(Color.White)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.follow_amount))
                Text(text = followInfo.total)
            }
            Text(
                text = stringResource(id = R.string.recent_follow),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            ToggleSwitch(
                onToggled = onToggled,
                description = stringResource(id = R.string.new_order),
                toggleState = toggleState,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    FollowList(
        followInfo = followInfo.followsList,
        halfScreenWidth = halfScreenWidth,
        modifier = modifier
    )
}
