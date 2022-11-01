package com.example.compose

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.thema.ShakaHomeTheme
import com.example.resource.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShakaHomeTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String? = null,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onActionClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        actions = {
            actionIcon ?: return@CenterAlignedTopAppBar
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = colors,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppBar() {
    ShakaHomeTheme {
        Surface {
            ShakaHomeTopAppBar(
                titleRes = R.string.app_name,
                actionIcon = Icons.Default.MoreVert,
                actionIconContentDescription = stringResource(id = R.string.action_icon)
            )
        }
    }
}