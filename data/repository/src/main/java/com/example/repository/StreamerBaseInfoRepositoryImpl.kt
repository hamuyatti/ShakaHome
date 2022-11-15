package com.example.repository

import com.example.data.remote.StreamerInfoRemoteDataSource
import com.example.response.StreamerBaseInfoResponse
import com.example.response.StreamerBaseInfoResponseContent
import com.example.irepository.StreamerBaseInfoRepository

class StreamerBaseInfoRepositoryImpl(
    private val streamerInfoRemoteDataSource: StreamerInfoRemoteDataSource
) : StreamerBaseInfoRepository {
    override suspend fun fetchStreamerBaseInfo(): StreamerBaseInfoResponse {
        return streamerInfoRemoteDataSource.fetchStreamerBaseInfo()
    }
}
