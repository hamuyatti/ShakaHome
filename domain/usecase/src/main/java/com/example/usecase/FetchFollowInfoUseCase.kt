package com.example.usecase

import com.example.irepository.StreamerFollowInfoRepository
import com.example.model.domain.FollowInfo
import kotlinx.coroutines.coroutineScope

class FetchFollowInfoUseCase(
    private val followInfoRepository: StreamerFollowInfoRepository
) {
    suspend operator fun invoke() : FollowInfo = coroutineScope {
        followInfoRepository.fetchStreamerFollowInfo()
    }
}