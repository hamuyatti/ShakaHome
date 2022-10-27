package com.example.compose

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SimpleFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    description: String = "",
    icon: ImageVector,
    tint: Color = LocalContentColor.current
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(imageVector = icon, contentDescription = description, tint = tint)
    }
}