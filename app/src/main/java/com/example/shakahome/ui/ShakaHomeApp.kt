@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.shakahome.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.core.LocalIntentManager
import com.example.shakahome.impl.IntentManagerImpl
import com.example.shakahome.navigation.ShakaHomeNavHost
import com.example.compose.thema.ShakaHomeTheme

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
                    drawerState = appState.drawerState,
                    drawerSheetContent = {
                        DrawerSheetContent(
                            onClickDrawerItem = appState::onClickDrawerItem,
                            selectedDrawerItem = appState.selectedItem
                        )
                    })
                {
                    Scaffold(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        bottomBar = {
                            if (appState.isShowBottomBar.not()) return@Scaffold
                            ShakaHomeBottomBar(
                                destinations = appState.TOP_LEVEL_DESTINATIONS,
                                onClickBottomBar = appState::onClickBottomBarItem,
                                currentDestination = appState.currentDestination,
                            )
                        }) { padding ->
                        ShakaHomeNavHost(
                            navController = appState.navController,
                            onSettingIconClick = appState::onClickSettingIcon,
                            modifier = Modifier
                                .absolutePadding(
                                    top = padding.calculateTopPadding(),
                                    // padding.calculateBottomPaddingに任せると異常にpaddingが付与されてしまうため
                                    // absolutePaddingでbottomだけ自前で指定した。
                                    bottom = 80.dp
                                )
                                .consumedWindowInsets(padding),
                        )
                    }
                }
            }
        }
    }
}
