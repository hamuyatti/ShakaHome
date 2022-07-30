package com.example.usecase

import com.example.irepository.StreamerFollowInfoRepository
import com.example.irepository.StreamerBaseInfoRepository
import com.example.model.domain.StreamerInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

// TODO: なんとなくusecaseで値まとめたけど、compose用に各局viewModelで分散して変数を置くなら不要な処理だったかもなぁ
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