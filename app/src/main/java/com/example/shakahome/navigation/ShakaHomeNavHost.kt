package com.example.shakahome.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.feature_drawer.navigation.settingGraph
import com.example.feature_drawer.navigation.tmpGraph
import com.example.feature_info.navigation.infoGraph
import com.example.feature_streaming.navigation.StreamingDestination
import com.example.feature_streaming.navigation.reportGraph
import com.example.feature_streaming.navigation.streamingGraph


@Composable
fun ShakaHomeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = StreamingDestination.route,
    onSettingIconClick: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        streamingGraph()
        infoGraph(onSettingIconClick)
        reportGraph()
        settingGraph()
        tmpGraph()
    }
}