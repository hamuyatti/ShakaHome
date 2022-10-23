package com.example.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ToggleSwitch(onToggled: (Boolean) -> Unit, description: String) {
    Column {
        Text(text = description)
        Switch(checked = true, onCheckedChange = onToggled)
    }
}