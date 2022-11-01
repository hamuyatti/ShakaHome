package com.example.feature_drawer.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.navigation.ShakaHomeNavigationDestination

object SettingsNavigation : ShakaHomeNavigationDestination {
    override val route: String = "settings_route"
}


fun NavGraphBuilder.settingGraph() {
    composable(SettingsNavigation.route) {
        com.example.feature_drawer.settings.ForSettingRoute()
    }
}