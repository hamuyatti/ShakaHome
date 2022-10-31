package com.example.shakahome.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.feature_drawer.navigation.settingGraph
import com.example.feature_drawer.navigation.tmpGraph
import com.example.feature_info.navigation.infoGraph
import com.example.feature_report.navigation.ReportDestination
import com.example.feature_report.navigation.reportGraph


@Composable
fun ShakaHomeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ReportDestination.route,
    onSettingIconClick: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        reportGraph()
        infoGraph(onSettingIconClick)
        settingGraph()
        tmpGraph()
    }
}