package com.example.usecase

import com.example.irepository.StreamerFollowInfoRepository
import com.example.irepository.StreamerInfoRepository
import com.example.model.StreamerInfo

//複数のrepository,apiを掛け合わせる予定
class FetchStreamerInfoUseCase(
    private val repository: StreamerInfoRepository,
    private val followInfoRepository: StreamerFollowInfoRepository
) {
    suspend operator fun invoke(): StreamerInfo {
        return repository.fetchStreamerInfo()
    }
}