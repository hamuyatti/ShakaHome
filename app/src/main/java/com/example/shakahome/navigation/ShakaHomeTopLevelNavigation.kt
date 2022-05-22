package com.example.shakahome.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grid3x3
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.outlined.Grid3x3
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.shakahome.R
import com.example.ui.navigation.InfoNavigation
import com.example.ui.navigation.ReportDestination

class ShakaHomeTopLevelNavigation(private val navController: NavController){

    fun navigateTo(destination: TopLevelDestination){
        navController.navigate(destination.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

}

data class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

val TOP_LEVEL_DESTINATION = listOf(
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