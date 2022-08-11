package com.example.ui.feature.info

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.model.CarouselModel
import com.example.ui.R
import com.example.ui.ShakaHomeTopAppBar
import com.example.ui.utils.ImageCarousel
import com.example.viewmodel.FollowInfoUiState
import com.example.viewmodel.StreamerBaseInfoUiState
import com.example.viewmodel.StreamerInfoViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ForInfoRoute(
    modifier: Modifier = Modifier,
    viewModel: StreamerInfoViewModel = hiltViewModel()
) {
    val baseInfoState by viewModel.baseInfoUiState.collectAsState()
    val followState by viewModel.followInfoUiState.collectAsState()

    val isRefreshing by viewModel.isRefreshing.collectAsState()
    InfoScreen(
        onRefresh = { viewModel.refresh() },
        baseInfoUiState = baseInfoState,
        followInfoUiState = followState,
        modifier = modifier,
        isRefreshing = isRefreshing
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit,
    baseInfoUiState: StreamerBaseInfoUiState,
    followInfoUiState: FollowInfoUiState,
    isRefreshing: Boolean,
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { onRefresh.invoke() },
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
                )
            }, containerColor = Color.Transparent
        ) { innerPadding ->
            val context = LocalContext.current

            LazyColumn(modifier = modifier.padding(innerPadding)) {
                BaseInfoFeed(
                    uiState = baseInfoUiState,
                    context = context,
                )
                FollowInfoFeed(
                    uiState = followInfoUiState,
                    context = context,
                )
            }
        }
    }
}

private fun LazyListScope.BaseInfoFeed(
    uiState: StreamerBaseInfoUiState,
    context: Context,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is StreamerBaseInfoUiState.Loading -> {}

        is StreamerBaseInfoUiState.Error -> {
            Toast.makeText(context, "エラーです", Toast.LENGTH_SHORT).show()
        }

        is StreamerBaseInfoUiState.Empty -> {}

        is StreamerBaseInfoUiState.Success -> {
            item {
                Text(
                    text = "名前",
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
                            CarouselModel(it.profileImageUrl, "プロフィール画像"),
                            CarouselModel(it.offlineImageUrl, "オフライン画像"),
                        )
                    )
                }
            }
            item {
                Text(
                    text = "フォロー総数",
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}

fun LazyListScope.FollowInfoFeed(
    uiState: FollowInfoUiState,
    context: Context,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        FollowInfoUiState.Empty -> {}
        FollowInfoUiState.Loading -> {}
        is FollowInfoUiState.Error -> {
            Toast.makeText(context, "エラーです", Toast.LENGTH_SHORT).show()
        }

        is FollowInfoUiState.Success -> {
            item {
                Text(
                    text = uiState.followInfo.total,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = "最近のフォロー",
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
                )
            }
            FollowList(
                followInfo = uiState.followInfo.followsInfo,
            )
        }
    }
}


