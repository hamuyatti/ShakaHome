package com.example.usecase

import com.example.irepository.StreamerFollowInfoRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class FetchMoreFollowInfoUseCase @Inject constructor(
    private val repository: StreamerFollowInfoRepository
) {
    suspend operator fun invoke(nextCursor: String) = repository.fetchMoreFollowInfo(nextCursor)
}