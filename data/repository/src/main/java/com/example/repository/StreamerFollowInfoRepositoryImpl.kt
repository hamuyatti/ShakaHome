package com.example.repository

import com.example.data.api.StreamerFollowInfoRemoteDataSource
import com.example.db.FollowLocalDataSource
import com.example.irepository.StreamerFollowInfoRepository
import com.example.model.domain.FollowInfo
import com.example.model.response.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamerFollowInfoRepositoryImpl(
    private val remoteDataSource: StreamerFollowInfoRemoteDataSource,
    private val localDataSource: FollowLocalDataSource
) : StreamerFollowInfoRepository {
    override suspend fun fetchStreamerFollowInfo(): FollowInfo =
        withContext(Dispatchers.IO) {
            val followList = remoteDataSource.fetchStreamerFollowInfo().asDomainModel()
            localDataSource.updateFollowInfoCache(followList)
            return@withContext followList
        }

    override suspend fun fetchMoreFollowInfo(nextCursor: String): FollowInfo {
        val followList = remoteDataSource.fetchStreamerMoreFollowInfo(nextCursor).asDomainModel()
        val newFollowList = followList.copy(
            followsInfo = localDataSource.followInfoCache?.followsInfo!! + followList.followsInfo
        )
        localDataSource.updateFollowInfoCache(newFollowList)
        return newFollowList
    }
}