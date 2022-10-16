@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.shakahome.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.core.LocalIntentManager
import com.example.shakahome.impl.IntentManagerImpl
import com.example.shakahome.navigation.ShakaHomeNavHost
import com.example.ui.thema.ShakaHomeTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ShakaHomeApp(
    appState: ShakaHomeAppState = rememberShakaHomeAppState()
) {
    ShakaHomeTheme {
        var showSplashScreen by remember { mutableStateOf(true) }
        if (showSplashScreen) {
            SplashScreen {
                showSplashScreen = false
            }
        } else {
            CompositionLocalProvider(
                LocalIntentManager provides IntentManagerImpl(LocalContext.current)
            ) {
                AppDrawer(
                    appState = appState,
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
                            ShakaHomeBottomBar(
                                destinations = appState.TOP_LEVEL_DESTINATIONS,
                                onNavigateToTopLevelDestination = appState::navigateTo,
                                currentDestination = appState.currentDestination
                            )
                        }) { padding ->
                        ShakaHomeNavHost(
                            navController = appState.navController,
                            modifier = Modifier
                                .padding(padding)
                                .consumedWindowInsets(padding),
                        )
                    }
                }
            }
        }
    }
}
