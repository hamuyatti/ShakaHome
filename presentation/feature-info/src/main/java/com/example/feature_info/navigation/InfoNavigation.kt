package com.example.feature_info.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.ShakaHomeNavigationDestination
import com.example.ui.feature.info.ForInfoRoute

object InfoNavigation : ShakaHomeNavigationDestination {
    override val route: String = "info_route"
}

fun NavGraphBuilder.infoGraph(onSettingIconClick : ()-> Unit) {
    composable(route = InfoNavigation.route) {
        ForInfoRoute(
            onSettingIconClick = onSettingIconClick
        )
    }
}