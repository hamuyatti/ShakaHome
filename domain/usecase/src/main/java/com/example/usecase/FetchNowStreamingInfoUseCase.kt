package com.example.usecase

import com.example.irepository.NowStreamingInfoRepository
import com.example.entity.NowStreamingInfo
import javax.inject.Inject

class FetchNowStreamingInfoUseCase @Inject constructor(
    private val nowStreamingInfoRepository: NowStreamingInfoRepository,
) {
    suspend operator fun invoke(): NowStreamingInfo? {
        return NowStreamingInfo.from(nowStreamingInfoRepository.fetchNowStreamingInfo())
    }
}