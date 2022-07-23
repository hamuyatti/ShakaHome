package com.example.ui.feature.report

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ui.R
import com.example.ui.ShakaHomeTopAppBar
import com.example.viewmodel.ReportUiState
import com.example.viewmodel.ReportViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ForReportRoute(
    modifier: Modifier = Modifier,
    viewModel: ReportViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    ReportScreen(
        isRefreshing = isRefreshing,
        onRefreshing = { viewModel.fetchNowStreamingInfo() },
        uiState = state
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    onRefreshing: () -> Unit,
    uiState: ReportUiState
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
                    uiState = uiState,
                    innerPadding = innerPadding,
                    context = context
                )
            }
        }
    }
}

private fun LazyListScope.NowStreamingInfo(
    uiState: ReportUiState,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    context: Context
) {
    when (uiState) {
        is ReportUiState.Loading -> {}

        is ReportUiState.Error -> {
            Toast.makeText(context, "エラーです", Toast.LENGTH_SHORT).show()
        }

        is ReportUiState.Empty -> {
            item {
                Text(
                    text = "現在の放送はありません。",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        is ReportUiState.Success -> {
            item {
                Column(modifier = modifier.padding(innerPadding)) {
                    Text(text = uiState.nowStreamingInfo.userName)
                    Text(text = uiState.nowStreamingInfo.title)
                    Text(text = uiState.nowStreamingInfo.viewerCount.toString())
                    Text(text = uiState.nowStreamingInfo.startedAt)
                    AsyncImage(
                        model = uiState.nowStreamingInfo.thumbnailUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                }
            }
        }
    }
}

