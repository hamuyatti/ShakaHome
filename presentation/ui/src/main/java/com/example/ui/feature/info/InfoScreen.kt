package com.example.ui.feature.info

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
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
import com.example.model.CarouselModel
import com.example.model.domain.FollowInfo
import com.example.resource.R
import com.example.ui.ShakaHomeTopAppBar
import com.example.ui.utils.ImageCarousel
import com.example.viewmodel.info.FollowInfoUiState
import com.example.viewmodel.info.StreamerBaseInfoUiState
import com.example.viewmodel.info.StreamerInfoViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ForInfoRoute(
    modifier: Modifier = Modifier,
    viewModel: StreamerInfoViewModel = hiltViewModel(),
    onSettingIconClick: () -> Unit
) {
    val baseInfoState by viewModel.baseInfoUiState.collectAsStateWithLifecycle()
    val followState by viewModel.followInfoUiState.collectAsStateWithLifecycle()

    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    InfoScreen(
        onRefresh = viewModel::onSwipeRefresh,
        onReachedBottom = viewModel::onReachBottom,
        baseInfoUiState = baseInfoState,
        followInfoUiState = followState,
        modifier = modifier,
        isRefreshing = isRefreshing,
        onSettingIconClick = onSettingIconClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    onReachedBottom: () -> Unit,
    baseInfoUiState: StreamerBaseInfoUiState,
    followInfoUiState: FollowInfoUiState,
    isRefreshing: Boolean,
    onSettingIconClick: () -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { onRefresh() },
        indicatorAlignment = Alignment.TopCenter,
        indicatorPadding = PaddingValues(100.dp)
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
            }, containerColor = Color.Transparent
        ) { innerPadding ->
            val context = LocalContext.current
            val listState = rememberLazyListState()
            val currentOnReachedBottom by rememberUpdatedState(onReachedBottom)
            val isReachedBottom by remember {
                derivedStateOf {
                    listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size == listState.layoutInfo.totalItemsCount
                }
            }
            LaunchedEffect(isReachedBottom) {
                snapshotFlow { isReachedBottom }
                    .collect { isReached ->
                        if (isReached) {
                            currentOnReachedBottom()
                        }
                    }
            }

            val screenWidth = LocalConfiguration.current.screenWidthDp
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(innerPadding)
            ) {
                BaseInfoFeed(
                    uiState = baseInfoUiState,
                    context = context,
                )
                FollowInfoFeed(
                    uiState = followInfoUiState,
                    context = context,
                    screenWidth = screenWidth
                )
            }
        }
    }
}

private fun LazyListScope.BaseInfoFeed(
    uiState: StreamerBaseInfoUiState,
    context: Context,
    modifier: Modifier = Modifier,
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
                    modifier = modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = uiState.baseInfo.displayName,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
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
    screenWidth: Int
) {
    when (uiState) {
        FollowInfoUiState.Empty -> {}
        FollowInfoUiState.Loading -> {}
        is FollowInfoUiState.Error -> {
            Toast.makeText(context, R.string.error_notice, Toast.LENGTH_SHORT).show()
        }

        is FollowInfoUiState.Success -> {
            FollowContent(followInfo = uiState.followInfo, screenWidth, modifier = modifier)
        }

        is FollowInfoUiState.MoreLoading -> {
            FollowContent(followInfo = uiState.followInfo, screenWidth, modifier = modifier)
        }
    }
}

private fun LazyListScope.FollowContent(
    followInfo: FollowInfo,
    screenWidth: Int,
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
        }
    }

    FollowList(followInfo = followInfo.followsInfo, screenWidth = screenWidth, modifier = modifier)
}
