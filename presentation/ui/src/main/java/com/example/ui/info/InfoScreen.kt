package com.example.ui.info

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ui.R
import com.example.ui.ShakaHomeTopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    onUpdate: () -> Unit,
    uiState: StreamerInfoUiState,
) {
    Scaffold(
        topBar = {
            ShakaHomeTopAppBar(
                actionIcon = Icons.Outlined.Settings,
                actionIconContentDescription = stringResource(
                    id = R.string.top_app_bar_action_button_content_desc
                )
            )
        }
    ) { innerPadding ->
        innerPadding
        LazyColumn {
            feed(uiState)
        }
    }
}


private fun LazyListScope.feed(
    streamerInfoUiState: StreamerInfoUiState
) {
    when(streamerInfoUiState){
        is StreamerInfoUiState.IsLoading -> {

        }

        is StreamerInfoUiState.Error -> {

        }
        is StreamerInfoUiState.Success -> {

        }
        is StreamerInfoUiState.Empty -> {

        }
    }

}