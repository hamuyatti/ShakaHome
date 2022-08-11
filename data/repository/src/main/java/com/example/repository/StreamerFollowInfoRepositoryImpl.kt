package com.example.repository

import com.example.data.api.StreamerFollowInfoRemoteDataSource
import com.example.db.FollowLocalDataSource
import com.example.irepository.StreamerFollowInfoRepository
import com.example.model.response.FollowInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamerFollowInfoRepositoryImpl(
    private val remoteDataSource: StreamerFollowInfoRemoteDataSource,
    private val localDataSource: FollowLocalDataSource
) : StreamerFollowInfoRepository {
    override suspend fun fetchStreamerFollowInfo(): FollowInfoResponse =
        withContext(Dispatchers.IO) {
            remoteDataSource.fetchStreamerFollowInfo()
        }

    override suspend fun fetchMoreFollowInfo(): FollowInfoResponse {
        TODO("Not yet implemented")
    }
}