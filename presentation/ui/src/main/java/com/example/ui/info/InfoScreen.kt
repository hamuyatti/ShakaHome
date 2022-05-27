package com.example.ui.info

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.viewmodel.StreamerInfoUiState
import com.example.viewmodel.StreamerInfoViewModel

@Composable
fun ForInfoRoute(
    modifier: Modifier = Modifier,
    viewModel: StreamerInfoViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    InfoScreen(onUpdate = { viewModel.fetchStreamerInfo() }, uiState = state, modifier = modifier)
}

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    onUpdate: () -> Unit,
    uiState: StreamerInfoUiState,
) {
    Scaffold() {
        
    }
}