package com.example.usecase

import com.example.irepository.StreamerBaseInfoRepository
import com.example.entity.StreamerBaseInfo
import javax.inject.Inject

class FetchStreamerBaseInfoUseCase @Inject constructor(
    private val baseInfoRepository: StreamerBaseInfoRepository
) {
    suspend operator fun invoke(): StreamerBaseInfo? {
        return StreamerBaseInfo.from(baseInfoRepository.fetchStreamerBaseInfo())
    }
}