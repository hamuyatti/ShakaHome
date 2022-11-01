package com.example.feature_drawer.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.compose.navigation.ShakaHomeNavigationDestination

object TmpNavigation : ShakaHomeNavigationDestination {
    override val route: String = "tmp_route"
}


fun NavGraphBuilder.tmpGraph() {
    composable(TmpNavigation.route) {
        com.example.feature_drawer.tmp.ForTmpRoute()
    }
}