package com.example.usecase

import com.example.irepository.StreamerFollowInfoRepository
import com.example.model.domain.FollowInfo
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class FetchFollowInfoUseCase @Inject constructor(
    private val followInfoRepository: StreamerFollowInfoRepository
) {
    suspend operator fun invoke() : FollowInfo = followInfoRepository.fetchStreamerFollowInfo()
}