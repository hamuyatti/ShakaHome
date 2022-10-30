package com.example.usecase

import com.example.entity.FollowInfo
import com.example.irepository.StreamerFollowInfoRepository
import javax.inject.Inject

class FetchMoreFollowInfoUseCase @Inject constructor(
    private val repository: StreamerFollowInfoRepository
) {
    suspend operator fun invoke(nextCursor: String): FollowInfo {
        return FollowInfo.from(repository.fetchMoreFollowInfo(nextCursor))
    }
}