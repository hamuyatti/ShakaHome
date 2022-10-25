package com.example.usecase

import com.example.irepository.NowStreamingInfoRepository
import com.example.model.domain.NowStreamingInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.coroutineScope

class FetchNowStreamingInfoUseCase(
    private val nowStreamingInfoRepository: NowStreamingInfoRepository,
) {
    suspend operator fun invoke(): NowStreamingInfo? = nowStreamingInfoRepository.fetchNowStreamingInfo()
}