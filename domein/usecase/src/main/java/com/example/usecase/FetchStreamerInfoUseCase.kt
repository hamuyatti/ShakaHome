package com.example.usecase

import com.example.irepository.StreamerInfoRepository
import com.example.model.StreamerInfo

class FetchStreamerInfoUseCase(
    private val repository: StreamerInfoRepository
) {
    operator fun invoke(): StreamerInfo {
        return repository.fetchStreamerInfo()
    }
}