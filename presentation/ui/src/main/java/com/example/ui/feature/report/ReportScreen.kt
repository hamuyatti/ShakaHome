package com.example.ui.feature.report

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ui.R
import com.example.ui.ShakaHomeTopAppBar
import com.example.viewmodel.NowStreamingInfoUiState
import com.example.viewmodel.ReportViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import timber.log.Timber

@Composable
fun ForReportRoute(
    modifier: Modifier = Modifier,
    viewModel: ReportViewModel = hiltViewModel()
) {
    val state by viewModel.nowStreamingInfoUiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    ReportScreen(
        isRefreshing = isRefreshing,
        onRefreshing = { viewModel.refresh() },
        nowStreamingInfoUiState = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefreshing: () -> Unit,
    nowStreamingInfoUiState: NowStreamingInfoUiState
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
                    context = context
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun LazyListScope.NowStreamingInfo(
    uiState: NowStreamingInfoUiState,
    modifier: Modifier = Modifier,
    context: Context
) {
    when (uiState) {
        is NowStreamingInfoUiState.Loading -> {}

        is NowStreamingInfoUiState.Error -> {
            Toast.makeText(context, "エラーです", Toast.LENGTH_SHORT).show()
        }

        is NowStreamingInfoUiState.Empty -> {
            item {
                Text(
                    text = "現在の放送はありません。",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        is NowStreamingInfoUiState.Success -> {
            item {
                Card(
                    Modifier
                        .padding(16.dp)
                        .wrapContentSize()
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        val urlComplementedWidth =
                            uiState.nowStreamingInfo.thumbnailUrl.replace("{width}", "1280")
                        val url = urlComplementedWidth.replace("{height}", "720")
                        AsyncImage(
                            model = url,
                            contentDescription = "aa",
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
                        Row{
                            Text(
                                text = "視聴者数 ",
                            )
                            Text(
                                text = uiState.nowStreamingInfo.viewerCount.toString(),
                                textAlign = TextAlign.Center
                            )
                        }
                        Row{
                            Text(
                                text = uiState.nowStreamingInfo.startedAt,
                            )
                        }
                    }
                }
            }
        }
    }
}

