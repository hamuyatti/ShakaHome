package com.example.shakahome.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ui.navigation.ReportDestination
import com.example.ui.navigation.infoGraph
import com.example.ui.navigation.reportGraph
import com.example.ui.navigation.settingGraph


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
    }
}