package com.example.shakahome

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.example.shakahome.ui.ShakaHomeApp
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val callbackOnItemClicked: (String) -> Unit = { it ->
            val uri = Uri.parse(it)
            val intent = Intent(ACTION_VIEW, uri)
            startActivity(intent)
        }

        setContent { ShakaHomeApp(calculateWindowSizeClass(this), callbackOnItemClicked) }
    }
}