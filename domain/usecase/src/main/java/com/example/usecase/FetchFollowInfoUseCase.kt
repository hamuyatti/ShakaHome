package com.example.usecase

import com.example.entity.FollowInfo
import com.example.irepository.StreamerFollowInfoRepository
import javax.inject.Inject

class FetchFollowInfoUseCase @Inject constructor(
    private val followInfoRepository: StreamerFollowInfoRepository
) {
    suspend operator fun invoke(): FollowInfo {
        return FollowInfo.from(followInfoRepository.fetchStreamerFollowInfo())
    }
}