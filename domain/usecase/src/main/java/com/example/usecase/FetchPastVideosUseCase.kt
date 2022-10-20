package com.example.usecase

import com.example.irepository.PastVideosRepository
import com.example.model.domain.PastVideosInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.coroutineScope

class FetchPastVideosUseCase(
    private val pastVideosRepository: PastVideosRepository
) {
    suspend operator fun invoke(): PastVideosInfo = coroutineScope {
        return@coroutineScope pastVideosRepository.fetchPastVideos().asDomainModel()
    }
}