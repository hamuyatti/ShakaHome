package com.example.feature_report.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.navigation.ShakaHomeNavigationDestination
import com.example.feature_report.ForReportRoute
import com.example.feature_report.ReportViewModel

object ReportDestination : ShakaHomeNavigationDestination {
    override val route: String = "report_route"
}

fun NavGraphBuilder.reportGraph() {
    composable(route = ReportDestination.route) {
        val viewModel: ReportViewModel = hiltViewModel()
        ForReportRoute(viewModel = viewModel)
    }
}