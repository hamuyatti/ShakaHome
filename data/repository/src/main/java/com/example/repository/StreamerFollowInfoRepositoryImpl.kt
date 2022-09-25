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
            val firstFollowList = remoteDataSource.fetchStreamerFollowInfo()
            val secondFollowList =
                remoteDataSource.fetchStreamerMoreFollowInfo(nextCursor = firstFollowList.pagination?.cursor!!)

            localDataSource.updateFollowInfoCache(secondFollowList.asDomainModel())

            val domain1 = firstFollowList.asDomainModel()
            val domain2 = secondFollowList.asDomainModel()

            return@withContext domain1.copy(
                followsInfo = domain1.followsInfo + domain2.followsInfo
            )
        }

    override suspend fun fetchMoreFollowInfo(): FollowInfo {
        val nextCursor = localDataSource.nextCursor?.cursor ?: throw Exception()
        val followResponse = remoteDataSource.fetchStreamerMoreFollowInfo(nextCursor)
        val followList = followResponse.asDomainModel()
        val newFollowList = followList.copy(
            followsInfo = followList.followsInfo + localDataSource.followInfoCache?.followsInfo!!
        )
        localDataSource.updateFollowInfoCache(newFollowList)
     //   localDataSource.updateNextCursor(followResponse.pagination)
        return newFollowList
    }
}