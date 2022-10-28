package com.example.repository

import com.example.data.api.StreamerInfoRemoteDataSource
import com.example.irepository.StreamerBaseInfoRepository
import com.example.model.domain.StreamerBaseInfo
import com.example.model.response.asDomainModel

class StreamerBaseInfoRepositoryImpl(
    private val streamerInfoRemoteDataSource: StreamerInfoRemoteDataSource
) : StreamerBaseInfoRepository {
    override suspend fun fetchStreamerBaseInfo(): StreamerBaseInfo =
        streamerInfoRemoteDataSource.fetchStreamerBaseInfo().asDomainModel()
}
