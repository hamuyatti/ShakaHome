package com.example.core

import androidx.compose.runtime.staticCompositionLocalOf

interface IntentManager {
    fun transition(url: String)
}

val LocalIntentManager = staticCompositionLocalOf<IntentManager> {
    error("CompositionLocal LocalShareManager not present")
}