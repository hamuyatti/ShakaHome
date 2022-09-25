package com.example.shakahome.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.ui.feature.drawer.settings.SettingsNavigation

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