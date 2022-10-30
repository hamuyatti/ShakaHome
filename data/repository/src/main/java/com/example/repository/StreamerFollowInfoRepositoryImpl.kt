package com.example.repository

import com.example.data.api.StreamerFollowInfoRemoteDataSource
import com.example.db.FollowLocalDataSource
import com.example.response.FollowInfoResponse
import com.example.irepository.StreamerFollowInfoRepository

class StreamerFollowInfoRepositoryImpl(
    private val remoteDataSource: StreamerFollowInfoRemoteDataSource,
    private val localDataSource: FollowLocalDataSource
) : StreamerFollowInfoRepository {
    override suspend fun fetchStreamerFollowInfo(): FollowInfoResponse {
        val followList = remoteDataSource.fetchStreamerFollowInfo()
        localDataSource.updateFollowInfoCache(followList)
        return followList
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