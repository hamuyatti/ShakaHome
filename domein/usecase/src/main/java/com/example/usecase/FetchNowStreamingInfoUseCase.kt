package com.example.usecase

import com.example.irepository.NowStreamingInfoRepository
import com.example.irepository.PastVideosRepository
import com.example.model.domain.NowStreamingInfo
import com.example.model.domain.ReportInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class FetchNowStreamingInfoUseCase(
    private val nowStreamingInfoRepository: NowStreamingInfoRepository,
    private val pastVideosRepository: PastVideosRepository
) {
    suspend operator fun invoke(): ReportInfo = coroutineScope {
        val nowStreamingInfoAsync = async { nowStreamingInfoRepository.fetchNowStreamingInfo() }
        val pastVideosAsync = async { pastVideosRepository.fetchPastVideos() }

        return@coroutineScope ReportInfo.from(
            streamingInfo = nowStreamingInfoAsync.await().asDomainModel(),
            pastVideosInfo = pastVideosAsync.await().asDomainModel()
        )
    }
}