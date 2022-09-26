package com.example.shakahome.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.WbTwilight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core.LocalIntentManager
import com.example.ui.navigation.SettingsNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    appState: ShakaHomeAppState,
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
    val intentManager = LocalIntentManager.current
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "アプリ内ページ", modifier = Modifier.padding(16.dp))
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

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(text = "外部リンク", modifier = Modifier.padding(16.dp))
        ExternalLinkItem.values().forEach { externalLinkItem ->
            NavigationDrawerItem(
                label = {
                    Text(text = stringResource(id = externalLinkItem.titleStringRes))
                },
                selected = false,
                onClick = { intentManager.transition(externalLinkItem.url) },
                icon = { Icon(imageVector = externalLinkItem.icon, contentDescription = null) })
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

enum class ExternalLinkItem(
    @StringRes val titleStringRes: Int,
    val icon: ImageVector,
    val url: String
) {
    Twitter(
        titleStringRes = com.example.core.R.string.external_link_twitter,
        icon = Icons.Outlined.WbTwilight,
        url = "https://twitter.com/avashaka"
    ),
    Instagram(
        titleStringRes = com.example.core.R.string.external_link_instagram,
        icon = Icons.Outlined.WbTwilight,
        url = "https://www.instagram.com/avashaka/"
    ),
    Wikipedia(
        titleStringRes = com.example.core.R.string.external_link_wikipedia,
        icon = Icons.Outlined.WbTwilight,
        url = "https://ja.wikipedia.org/wiki/SHAKA"
    ),
}