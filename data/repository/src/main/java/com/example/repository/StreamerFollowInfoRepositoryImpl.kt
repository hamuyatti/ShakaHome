package com.example.repository

import com.example.data.api.StreamerFollowInfoRemoteDataSource
import com.example.irepository.StreamerFollowInfoRepository
import com.example.model.response.FollowInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamerFollowInfoRepositoryImpl(
    private val dataSource: StreamerFollowInfoRemoteDataSource
) : StreamerFollowInfoRepository {
    override suspend fun fetchStreamerFollowInfo(): FollowInfoResponse =
        withContext(Dispatchers.IO) {
            dataSource.fetchStreamerFollowInfo()
        }
}