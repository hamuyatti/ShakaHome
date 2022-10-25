package com.example.repository

import com.example.data.api.StreamerInfoRemoteDataSource
import com.example.irepository.StreamerBaseInfoRepository
import com.example.model.domain.StreamerBaseInfo
import com.example.model.response.StreamerBaseInfoResponse
import com.example.model.response.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamerBaseInfoRepositoryImpl(
    private val streamerInfoRemoteDataSource: StreamerInfoRemoteDataSource
) : StreamerBaseInfoRepository {
    override suspend fun fetchStreamerBaseInfo(): StreamerBaseInfo =
        withContext(Dispatchers.IO) {
            streamerInfoRemoteDataSource.fetchStreamerBaseInfo().asDomainModel()
        }
}
