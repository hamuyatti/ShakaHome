package com.example.ui

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

@Composable
fun ShakaHomeTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int = R.string.app_name,
    onActionClick: () -> Unit = {},
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        modifier = modifier,
        colors = colors
    )
}