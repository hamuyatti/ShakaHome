package com.example.shakahome.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val currentOnTimeOut by rememberUpdatedState(onTimeout)

    Image(
        painterResource(id = com.google.android.material.R.drawable.abc_scrubber_control_off_mtrl_alpha),
        contentDescription = null
    )

    LaunchedEffect(currentOnTimeOut) {
        delay(SplashWaitTime)
        currentOnTimeOut()
    }
}