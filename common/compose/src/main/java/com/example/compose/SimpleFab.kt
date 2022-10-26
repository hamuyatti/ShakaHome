package com.example.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleFab(modifier: Modifier, onClick: () -> Unit, description: String) {
    FloatingActionButton(onClick = onClick, modifier = modifier) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = description)
    }
}