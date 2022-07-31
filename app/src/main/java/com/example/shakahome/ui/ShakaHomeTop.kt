package com.example.shakahome.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shakahome.navigation.ShakaHomeNavHost
import com.example.shakahome.navigation.ShakaHomeTopLevelNavigation
import com.example.shakahome.navigation.TOP_LEVEL_DESTINATIONS
import com.example.shakahome.navigation.TopLevelDestination
import com.example.ui.ClearRippleTheme
import com.example.ui.thema.ShakaHomeTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ShakaHomeApp(
    windowSizeClass: WindowSizeClass,
    callbackOnItemClicked: (String) -> Unit
) {
    ShakaHomeTheme {
        val navController = rememberNavController()
        val shakaHomeTopLevelNavigation = remember(navController) {
            ShakaHomeTopLevelNavigation(navController)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            modifier = Modifier,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                    ShakaHomeBottomBar(
                        onNavigateToTopLevelDestination = shakaHomeTopLevelNavigation::navigateTo,
                        currentDestination = currentDestination
                    )
                }
            }
        ) { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal
                        )
                    )
            ) {
                if (windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact) {
                    ShakaHomeNavRail(
                        onNavigateToTopLevelDestination = shakaHomeTopLevelNavigation::navigateTo,
                        currentDestination = currentDestination,
                        modifier = Modifier.safeDrawingPadding()
                    )
                }

                ShakaHomeNavHost(
                    windowSizeClass = windowSizeClass,
                    navController = navController,
                    modifier = Modifier
                        .padding(padding)
                        .consumedWindowInsets(padding),
                    callbackOnItemClicked = callbackOnItemClicked
                )
            }
        }
    }
}

@Composable
private fun ShakaHomeNavRail(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        TOP_LEVEL_DESTINATIONS.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationRailItem(
                selected = selected,
                onClick = { onNavigateToTopLevelDestination(destination) },
                icon = {
                    Icon(
                        if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}


@Composable
private fun ShakaHomeBottomBar(
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
    Surface(color = MaterialTheme.colorScheme.surface) {
        CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
            NavigationBar(
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                    )
                ),
                tonalElevation = 0.dp
            ) {

                TOP_LEVEL_DESTINATIONS.forEach { destination ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == destination.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = { onNavigateToTopLevelDestination(destination) },
                        icon = {
                            Icon(
                                if (selected) {
                                    destination.selectedIcon
                                } else {
                                    destination.unselectedIcon
                                },
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(destination.iconTextId)) }
                    )
                }
            }
        }
    }
}
