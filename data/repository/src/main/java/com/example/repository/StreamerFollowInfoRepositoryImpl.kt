package com.example.repository

import com.example.data.api.StreamerFollowInfoRemoteDataSource
import com.example.irepository.StreamerFollowInfoRepository
import com.example.model.FollowInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamerFollowInfoRepositoryImpl(
    private val dataSource: StreamerFollowInfoRemoteDataSource
) : StreamerFollowInfoRepository {
    override suspend fun fetchStreamerFollowInfo(useDummy : Boolean ): FollowInfoResponse =
        withContext(Dispatchers.IO) {
            if (useDummy) {
                FollowInfoResponse.dummyData()
            } else {
                dataSource.fetchStreamerFollowInfo()
            }
        }
}