package com.example.shakahome.ui

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.shakahome.navigation.TopLevelDestination
import com.example.ui.ClearRippleTheme

@Composable
fun ShakaHomeBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToTopLevelDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
    Surface(color = MaterialTheme.colorScheme.surface) {
        CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
            NavigationBar(
                tonalElevation = 0.dp
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
