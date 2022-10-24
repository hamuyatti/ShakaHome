package com.example.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ToggleSwitch(
    onToggled: (Boolean) -> Unit,
    description: String,
    toggleState: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = description)
        Switch(checked = toggleState, onCheckedChange = onToggled)
    }
}