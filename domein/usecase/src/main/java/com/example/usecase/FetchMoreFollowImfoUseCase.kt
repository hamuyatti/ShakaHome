package com.example.usecase

import com.example.irepository.StreamerFollowInfoRepository
import kotlinx.coroutines.coroutineScope

class FetchMoreFollowInfoUseCase(
    private val repository: StreamerFollowInfoRepository
) {
    suspend operator fun invoke() = coroutineScope {
        return@coroutineScope repository.fetchMoreFollowInfo()
    }
}