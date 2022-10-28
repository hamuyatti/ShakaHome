package com.example.usecase

import com.example.irepository.StreamerBaseInfoRepository
import com.example.model.domain.StreamerBaseInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class FetchStreamerBaseInfoUseCase @Inject constructor(
    private val baseInfoRepository: StreamerBaseInfoRepository
) {
    suspend operator fun invoke(): StreamerBaseInfo =
        baseInfoRepository.fetchStreamerBaseInfo()
}