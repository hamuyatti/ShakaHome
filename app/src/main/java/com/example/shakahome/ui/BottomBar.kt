package com.example.shakahome.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.shakahome.navigation.TopLevelDestination
import com.example.ui.ClearRippleTheme

@Composable
fun ShakaHomeBottomBar(
    destinations: List<TopLevelDestination>,
    onClickBottomBar: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
            NavigationBar(
                tonalElevation = 0.dp,
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                    )
                )
            ) {
                destinations.forEach { destination ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == destination.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = { onClickBottomBar(destination) },
                        icon = {
                            Icon(
                                if (selected) {
                                    destination.selectedIcon
                                } else {
                                    destination.unselectedIcon
                                }, contentDescription = null
                            )
                        },
                    )
                }
            }
        }
    }
}
