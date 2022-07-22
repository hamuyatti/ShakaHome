package com.example.ui.feature.report

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.R
import com.example.ui.ShakaHomeTopAppBar
import com.example.viewmodel.ReportUiState
import com.example.viewmodel.ReportViewModel

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
        innerPadding

    }
}
