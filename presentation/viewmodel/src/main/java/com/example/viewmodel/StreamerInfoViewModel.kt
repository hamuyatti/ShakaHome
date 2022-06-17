package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecase.FetchStreamerInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StreamerInfoViewModel @Inject constructor(
    private val useCase: FetchStreamerInfoUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<StreamerInfoUiState> =
        MutableStateFlow(StreamerInfoUiState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        fetchStreamerInfo()
    }

    fun fetchStreamerInfo() {
        viewModelScope.launch {
            _uiState.update { StreamerInfoUiState.Loading }
            runCatching {
                useCase()
            }.onSuccess { info ->
                Timber.d("${info}")
                _uiState.update { StreamerInfoUiState.Success(info) }
            }.onFailure { e ->
                _uiState.update { StreamerInfoUiState.Error(e) }
            }
        }
    }

}