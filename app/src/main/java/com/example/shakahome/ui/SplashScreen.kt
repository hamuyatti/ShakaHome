package com.example.shakahome.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 1500

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    val currentOnTimeOut by rememberUpdatedState(onTimeout)

    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Image(
            painterResource(id = com.example.core.R.drawable.shaka),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }

    LaunchedEffect(Unit) {
        delay(SplashWaitTime)
        currentOnTimeOut()
    }
}

@Preview
@Composable
fun PreviewSplashScreen(){
    SplashScreen {
        {}
    }
}