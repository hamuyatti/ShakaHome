package com.example.usecase

import com.example.irepository.StreamerFollowInfoRepository
import com.example.irepository.StreamerBaseInfoRepository
import com.example.model.domain.StreamerInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

//複数のrepository,apiを掛け合わせる予定
class FetchStreamerInfoUseCase(
    private val baseInfoRepository: StreamerBaseInfoRepository,
    private val followInfoRepository: StreamerFollowInfoRepository
) {
    suspend operator fun invoke(): StreamerInfo = coroutineScope {
        val baseInfoAsync = async { baseInfoRepository.fetchStreamerBaseInfo() }
        val followInfoAsync = async { followInfoRepository.fetchStreamerFollowInfo() }
        return@coroutineScope StreamerInfo.from(
            baseInfo = baseInfoAsync.await().asDomainModel(),
            followInfo = followInfoAsync.await().asDomainModel()
        )
    }
}