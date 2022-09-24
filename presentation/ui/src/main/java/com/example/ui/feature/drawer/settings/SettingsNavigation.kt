package com.example.ui.feature.drawer.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.navigation.ShakaHomeNavigationDestination

object SettingsNavigation : ShakaHomeNavigationDestination{
    override val route: String = "settings_route"
}


fun NavGraphBuilder.settingGraph(){
    composable(SettingsNavigation.route){
        ForSettingRoute()
    }
}