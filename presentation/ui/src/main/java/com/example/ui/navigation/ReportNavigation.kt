package com.example.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.feature.report.ForReportRoute

object ReportDestination : ShakaHomeNavigationDestination {
    override val route: String = "report_route"
}

fun NavGraphBuilder.reportGraph() {
    composable(route = ReportDestination.route) {
        ForReportRoute()
    }
}