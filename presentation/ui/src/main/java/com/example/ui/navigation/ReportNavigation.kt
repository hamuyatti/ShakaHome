package com.example.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object ReportDestination : ShakaHomeNavigationDestination{
    override val route: String = "report_route"
    override val destination: String = "report_destination"
}

fun NavGraphBuilder.reportGraph(
    windowSizeClass: WindowSizeClass
){
    composable(route = ReportDestination.route){

    }
}