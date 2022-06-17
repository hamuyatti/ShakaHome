package com.example.ui.feature.report

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ui.R
import com.example.ui.ShakaHomeTopAppBar

@Composable
fun ForReportRoute(
    modifier: Modifier = Modifier,
) {
    ReportScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen() {
    Scaffold(
        topBar = {
            ShakaHomeTopAppBar(
                titleRes = R.string.app_name,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.windowInsetsPadding(
                    WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
                ),
            )
        }, containerColor = Color.Transparent
    ) { innerPadding ->
        innerPadding

    }
}
