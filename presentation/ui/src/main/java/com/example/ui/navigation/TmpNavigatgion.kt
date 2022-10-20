package com.example.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.feature.drawer.tmp.ForTmpRoute

object TmpNavigation : ShakaHomeNavigationDestination {
    override val route: String = "tmp_route"
}


fun NavGraphBuilder.tmpGraph() {
    composable(TmpNavigation.route) {
        ForTmpRoute()
    }
}