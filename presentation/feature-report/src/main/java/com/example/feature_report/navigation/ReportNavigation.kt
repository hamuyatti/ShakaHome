package com.example.feature_report.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.navigation.ShakaHomeNavigationDestination
import com.example.feature_report.ForReportRoute

object ReportNavigation : ShakaHomeNavigationDestination {
    override val route: String = "info_route"
}

fun NavGraphBuilder.reportGraph(){
    composable(route = ReportNavigation.route){
        ForReportRoute()
    }
}