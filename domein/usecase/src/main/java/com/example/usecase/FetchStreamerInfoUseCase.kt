package com.example.usecase

import com.example.irepository.StreamerFollowInfoRepository
import com.example.irepository.StreamerBaseInfoRepository
import com.example.model.StreamerBaseInfo

//複数のrepository,apiを掛け合わせる予定
class FetchStreamerInfoUseCase(
    private val baseInfoRepository: StreamerBaseInfoRepository,
    private val followInfoRepository: StreamerFollowInfoRepository
) {
    suspend operator fun invoke(): StreamerBaseInfo {
        return baseInfoRepository.fetchStreamerBaseInfo()
    }
}