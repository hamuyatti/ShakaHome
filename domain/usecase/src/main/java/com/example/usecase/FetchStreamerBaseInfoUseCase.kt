package com.example.usecase

import com.example.irepository.StreamerBaseInfoRepository
import com.example.model.domain.StreamerBaseInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.coroutineScope

class FetchStreamerBaseInfoUseCase(
    private val baseInfoRepository: StreamerBaseInfoRepository
) {
    suspend operator fun invoke(): StreamerBaseInfo =
        baseInfoRepository.fetchStreamerBaseInfo()
}