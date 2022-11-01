package com.example.usecase

import com.example.entity.PastVideosInfo
import com.example.irepository.PastVideosRepository
import javax.inject.Inject

class FetchPastVideosUseCase @Inject constructor(
    private val pastVideosRepository: PastVideosRepository
) {
    suspend operator fun invoke(): PastVideosInfo {
        return PastVideosInfo.from(pastVideosRepository.fetchPastVideos())
    }
}