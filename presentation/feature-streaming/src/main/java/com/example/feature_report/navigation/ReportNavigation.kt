package com.example.feature_report.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.navigation.ShakaHomeNavigationDestination
import com.example.feature_report.ForStreamingRoute

object StreamingDestination : ShakaHomeNavigationDestination {
    override val route: String = "report_route"
}

fun NavGraphBuilder.streamingGraph() {
    composable(route = StreamingDestination.route) {
        ForStreamingRoute()
    }
}