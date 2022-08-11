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
            val followList = remoteDataSource.fetchStreamerFollowInfo()
            localDataSource.updateFollowInfoCache(followList.asDomainModel())
            localDataSource.updateNextCursor(followList.pagination)
            return@withContext followList.asDomainModel()
        }

    override suspend fun fetchMoreFollowInfo(): FollowInfo {
        val followResponse = remoteDataSource.fetchStreamerFollowInfo(localDataSource.nextCursor)
        val followList = followResponse.asDomainModel()
        val newFollowList = followList.copy(
            followsInfo = followList.followsInfo + localDataSource.followInfoCache?.followsInfo!!
        )
        localDataSource.updateFollowInfoCache(newFollowList)
        localDataSource.updateNextCursor(followResponse.pagination)
        return newFollowList
    }
}