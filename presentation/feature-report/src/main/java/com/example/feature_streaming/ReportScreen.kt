package com.example.feature_streaming

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ForReportRoute(
    modifier: Modifier = Modifier,
    viewModel: ReportViewModel = hiltViewModel()
) {
    ReportScreen(
        viewModel = viewModel
    )
}

@Composable
fun ReportScreen(
    viewModel: ReportViewModel
) {

}