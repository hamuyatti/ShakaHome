package com.example.shakahome.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grid3x3
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.outlined.Grid3x3
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shakahome.R
import com.example.shakahome.navigation.TopLevelDestination
import com.example.ui.navigation.InfoNavigation
import com.example.ui.navigation.ReportDestination


@Composable
fun rememberShakaHomeAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController()
): ShakaHomeAppState {
    return remember(navController, windowSizeClass) {
        ShakaHomeAppState(navController = navController, windowInsetSizeClass = windowSizeClass)
    }
}

@Stable
class ShakaHomeAppState(
    val navController: NavHostController,
    val windowInsetSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        get() = windowInsetSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
                windowInsetSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    fun navigateTo(destination: TopLevelDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val TOP_LEVEL_DESTINATIONS = listOf(
        TopLevelDestination(
            route = ReportDestination.route,
            selectedIcon = Icons.Filled.Upcoming,
            unselectedIcon = Icons.Outlined.Upcoming,
            iconTextId = R.string.report
        ),
        TopLevelDestination(
            route = InfoNavigation.route,
            selectedIcon = Icons.Filled.Grid3x3,
            unselectedIcon = Icons.Outlined.Grid3x3,
            iconTextId = R.string.info
        )
    )

}