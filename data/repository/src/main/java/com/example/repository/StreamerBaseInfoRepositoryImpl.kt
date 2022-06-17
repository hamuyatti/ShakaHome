package com.example.repository

import com.example.data.api.StreamerInfoRemoteDataSource
import com.example.irepository.StreamerBaseInfoRepository
import com.example.model.BuildConfig
import com.example.model.StreamerBaseInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class StreamerBaseInfoRepositoryImpl(
    private val streamerInfoRemoteDataSource: StreamerInfoRemoteDataSource
) : StreamerBaseInfoRepository {
    override suspend fun fetchStreamerBaseInfo(): StreamerBaseInfo =
        withContext(Dispatchers.IO) {
            if(BuildConfig.DEBUG){
                delay(2000)
                StreamerBaseInfo.dummyData()
            }else{
                streamerInfoRemoteDataSource.fetchStreamerBaseInfo()
            }
        }
}