@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.shakahome.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.shakahome.navigation.ShakaHomeNavHost
import com.example.shakahome.navigation.TopLevelDestination
import com.example.ui.ClearRippleTheme
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
