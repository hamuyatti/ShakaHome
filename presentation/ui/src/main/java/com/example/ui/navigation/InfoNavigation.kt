package com.example.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object InfoNavigation : ShakaHomeNavigationDestination {
    override val route: String = "info_route"
    override val destination: String = "info_destination"
}

//参考にしたアプリのままwindowSizeClassを持ってきたけど、一旦使わなそう
fun NavGraphBuilder.infoGraph(
    windowSizeClass: WindowSizeClass
){
    composable(route = InfoNavigation.route){

    }
}