package com.example.feature_report

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.compose.ShakaHomeTopAppBar
import com.example.core.LocalIntentManager
import com.example.resource.R
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ForReportRoute(
    modifier: Modifier = Modifier,
    viewModel: ReportViewModel = hiltViewModel(),
) {
    val nowStreamInfoState by viewModel.nowStreamingInfoUiState.collectAsStateWithLifecycle()
    val pastVideosInfoState by viewModel.pastVideosInfoState.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()

    ReportScreen(
        modifier = modifier,
        isRefreshing = isRefreshing,
        onRefreshing = viewModel::onSwipeRefresh,
        nowStreamingInfoUiState = nowStreamInfoState,
        pastVideosInfoState = pastVideosInfoState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefreshing: () -> Unit,
    nowStreamingInfoUiState: NowStreamingInfoState,
    pastVideosInfoState: PastVideosInfoState,
) {
    SwipeRefresh(
        modifier = modifier.fillMaxHeight(),
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { onRefreshing.invoke() },
        indicatorAlignment = Alignment.TopCenter,
        indicatorPadding = PaddingValues(
            100.dp
        )
    ) {
        Scaffold(
            topBar = {
                ShakaHomeTopAppBar(
                    titleRes = R.string.app_name,
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
            LazyColumn(
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                NowStreamingInfo(
                    uiState = nowStreamingInfoUiState,
                    context = context,
                )
                PastVideosInfo(
                    uiState = pastVideosInfoState,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.NowStreamingInfo(
    uiState: NowStreamingInfoState,
    modifier: Modifier = Modifier,
    context: Context,
) {
    when (uiState) {
        is NowStreamingInfoState.Loading -> {}

        is NowStreamingInfoState.Error -> {
            Toast.makeText(context, context.getString(R.string.error_notice), Toast.LENGTH_SHORT)
                .show()
        }

        is NowStreamingInfoState.Empty -> {
            stickyHeader {
                Text(
                    text = stringResource(id = R.string.no_now_streaming),
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    textAlign = TextAlign.Center,
                )
            }
        }

        is NowStreamingInfoState.Success -> {
            stickyHeader {
                Column(
                    Modifier
                        .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .wrapContentSize()
                        .background(Color.White),
                ) {
                    Card {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        ) {
                            Text(
                                text = stringResource(id = R.string.now_streaming),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            AsyncImage(
                                model = uiState.nowStreamingInfo.thumbnailUrl,
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                            Text(
                                text = uiState.nowStreamingInfo.title,
                                textAlign = TextAlign.Start,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = stringResource(id = R.string.viewer_count),
                                )
                                Text(
                                    text = uiState.nowStreamingInfo.viewerCount.toString(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Row {
                                Text(
                                    text = uiState.nowStreamingInfo.startedAt,
                                )
                            }
                        }
                    }

                    Divider(modifier.padding(8.dp))
                }
            }
        }
    }
}

private fun LazyListScope.PastVideosInfo(
    uiState: PastVideosInfoState,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is PastVideosInfoState.Empty -> {

        }

        is PastVideosInfoState.Error -> {

        }

        is PastVideosInfoState.Loading -> {

        }

        is PastVideosInfoState.Success -> {
            items(uiState.pastVideosState.pastVideos) { item ->
                val intentManager = LocalIntentManager.current
                Card(
                    Modifier
                        .padding(16.dp)
                        .wrapContentSize()
                        .clickable { intentManager.transition(item.url) },
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        if (item.thumbnailUrl.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.no_set_image),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        } else {
                            AsyncImage(
                                model = item.thumbnailUrl,
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                        Text(
                            text = item.title,
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row {
                            Text(
                                text = stringResource(id = R.string.viewer_count),
                            )
                            Text(
                                text = item.viewCount.toString(),
                                textAlign = TextAlign.Center
                            )
                        }
                        Row {
                            Text(
                                text = "${item.createdAt}~  ",
                            )
                            Text(
                                text = item.duration
                            )
                        }
                    }
                }
            }
        }
    }
}
