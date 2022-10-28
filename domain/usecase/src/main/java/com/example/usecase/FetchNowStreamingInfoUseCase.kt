package com.example.usecase

import com.example.irepository.NowStreamingInfoRepository
import com.example.model.domain.NowStreamingInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class FetchNowStreamingInfoUseCase @Inject constructor(
    private val nowStreamingInfoRepository: NowStreamingInfoRepository,
) {
    suspend operator fun invoke(): NowStreamingInfo? = nowStreamingInfoRepository.fetchNowStreamingInfo()
}