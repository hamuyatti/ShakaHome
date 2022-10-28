package com.example.usecase

import com.example.irepository.PastVideosRepository
import com.example.model.domain.PastVideosInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class FetchPastVideosUseCase @Inject constructor(
    private val pastVideosRepository: PastVideosRepository
) {
    suspend operator fun invoke(): PastVideosInfo =
        pastVideosRepository.fetchPastVideos()
}