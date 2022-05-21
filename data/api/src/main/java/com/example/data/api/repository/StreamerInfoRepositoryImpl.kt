package com.example.data.api.repository

import com.example.data.api.StreamerInfoRemoteDataSource
import com.example.irepository.StreamerInfoRepository
import com.example.model.StreamerInfo

class StreamerInfoRepositoryImpl(
    private val streamerInfoRemoteDataSource: StreamerInfoRemoteDataSource
) : StreamerInfoRepository {
    override fun fetchStreamerInfo(): StreamerInfo {
        return streamerInfoRemoteDataSource.fetchStreamerInfo()
    }
}