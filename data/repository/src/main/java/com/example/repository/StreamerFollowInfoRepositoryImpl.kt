package com.example.repository

import com.example.core.CoroutineDispatcherProvider
import com.example.data.remote.StreamerFollowInfoRemoteDataSource
import com.example.local.FollowLocalDataSource
import com.example.response.FollowInfoResponse
import com.example.irepository.StreamerFollowInfoRepository
import kotlinx.coroutines.withContext

class StreamerFollowInfoRepositoryImpl(
    private val remoteDataSource: StreamerFollowInfoRemoteDataSource,
    private val localDataSource: FollowLocalDataSource,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : StreamerFollowInfoRepository {
    override suspend fun fetchStreamerFollowInfo(): FollowInfoResponse {
        return withContext(coroutineDispatcherProvider.io){
            val followList = remoteDataSource.fetchStreamerFollowInfo()
            localDataSource.updateFollowInfoCache(followList)
            followList
        }
    }


    override suspend fun fetchMoreFollowInfo(nextCursor: String): FollowInfoResponse {
        val followList = remoteDataSource.fetchStreamerMoreFollowInfo(nextCursor)
        val newFollowList = followList.copy(
            data = localDataSource.followInfoCache?.data!! + followList.data
        )
        localDataSource.updateFollowInfoCache(newFollowList)
        return newFollowList
    }
}