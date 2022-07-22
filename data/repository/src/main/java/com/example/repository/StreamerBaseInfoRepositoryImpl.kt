package com.example.repository

import com.example.data.api.StreamerInfoRemoteDataSource
import com.example.irepository.StreamerBaseInfoRepository
import com.example.model.response.StreamerBaseInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamerBaseInfoRepositoryImpl(
    private val streamerInfoRemoteDataSource: StreamerInfoRemoteDataSource
) : StreamerBaseInfoRepository {
    override suspend fun fetchStreamerBaseInfo(): StreamerBaseInfoResponse =
        withContext(Dispatchers.IO) {
            streamerInfoRemoteDataSource.fetchStreamerBaseInfo()
        }
}
