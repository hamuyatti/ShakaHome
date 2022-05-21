package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.usecase.FetchStreamerInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StreamerInfoViewModel @Inject constructor(
    private val useCase: FetchStreamerInfoUseCase
) : ViewModel() {

}