@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.shakahome.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.shakahome.R
import com.example.shakahome.navigation.ShakaHomeNavHost
import com.example.shakahome.navigation.TopLevelDestination
import com.example.ui.ClearRippleTheme
import com.example.ui.feature.drawer.settings.SettingsNavigation
import com.example.ui.thema.ShakaHomeTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ShakaHomeApp(
    windowSizeClass: WindowSizeClass,
    callbackOnItemClicked: (String) -> Unit,
    appState: ShakaHomeAppState = rememberShakaHomeAppState(windowSizeClass = windowSizeClass)
) {
    ShakaHomeTheme {
        AppDrawer(
            appState = appState,
            windowSizeClass = windowSizeClass,
            drawerSheetContent = {
                DrawerSheetContent(
                    onClickDrawerItem = appState::onClickDrawerItem,
                    selectedDrawerItem = appState.selectedItem
                )
            })
        {
            Scaffold(
                modifier = Modifier,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        ShakaHomeBottomBar(
                            destinations = appState.TOP_LEVEL_DESTINATIONS,
                            onNavigateToTopLevelDestination = appState::navigateTo,
                            currentDestination = appState.currentDestination
                        )
                    }
                }) { padding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal
                            )
                        )
                ) {
                    if (appState.shouldShowNavRail) {
                        ShakaHomeNavRail(
                            destinations = appState.TOP_LEVEL_DESTINATIONS,
                            onNavigateToTopLevelDestination = appState::navigateTo,
                            currentDestination = appState.currentDestination,
                            modifier = Modifier.safeDrawingPadding()
                        )
                    }

                    ShakaHomeNavHost(
                        windowSizeClass = windowSizeClass,
                        navController = appState.navController,
                        modifier = Modifier
                            .padding(padding)
                            .consumedWindowInsets(padding),
                        callbackOnItemClicked = callbackOnItemClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun ShakaHomeNavRail(
    destinations: List<TopLevelDestination>,
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationRail(modifier = modifier) {
        destinations.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationRailItem(selected = selected,
                onClick = { onNavigateToTopLevelDestination(destination) },
                icon = {
                    Icon(
                        if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) })
        }
    }
}


@Composable
private fun ShakaHomeBottomBar(
    destinations: List<TopLevelDestination>,
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
                ), tonalElevation = 0.dp
            ) {
                destinations.forEach { destination ->
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
                                }, contentDescription = null
                            )
                        },
                        label = { Text(stringResource(destination.iconTextId)) })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    windowSizeClass: WindowSizeClass,
    appState: ShakaHomeAppState = rememberShakaHomeAppState(windowSizeClass = windowSizeClass),
    drawerSheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = appState.drawerState,
        drawerContent = { ModalDrawerSheet { drawerSheetContent() } }
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.DrawerSheetContent(
    selectedDrawerItem: DrawerItem?,
    onClickDrawerItem: (DrawerItem) -> Unit
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        DrawerItem.values().forEach { drawerItem ->
            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(id = drawerItem.titleStringRes))
                },
                selected = drawerItem == selectedDrawerItem,
                onClick = { onClickDrawerItem(drawerItem) },
                icon = {
                    Icon(imageVector = drawerItem.icon, contentDescription = null)
                }
            )
        }
    }
}


enum class DrawerItem(
    @StringRes val titleStringRes: Int,
    val icon: ImageVector,
    val navRoute: String
) {
    Settings(
        titleStringRes = com.example.core.R.string.drawer_menu_setting,
        icon = Icons.Outlined.Settings,
        navRoute = SettingsNavigation.route
    ),
    Tmp(
        titleStringRes = com.example.core.R.string.drawer_menu_tmp,
        icon = Icons.Outlined.Done,
        navRoute = ""
    )
}