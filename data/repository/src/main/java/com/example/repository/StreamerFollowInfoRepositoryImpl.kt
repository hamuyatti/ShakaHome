package com.example.repository

import com.example.data.api.StreamerFollowInfoRemoteDataSource
import com.example.irepository.StreamerFollowInfoRepository
import com.example.model.FollowInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamerFollowInfoRepositoryImpl(
    private val dataSource: StreamerFollowInfoRemoteDataSource
) : StreamerFollowInfoRepository {
    override suspend fun fetchStreamerFollowInfo(): FollowInfo =
        withContext(Dispatchers.IO) {
//            if (BuildConfig.DEBUG) {
//                FollowInfo.dummyData()
//            } else {
                dataSource.fetchStreamerFollowInfo()
//            }
        }
}