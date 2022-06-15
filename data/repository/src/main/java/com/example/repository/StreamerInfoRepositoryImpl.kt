package com.example.repository

import com.example.data.api.StreamerInfoRemoteDataSource
import com.example.irepository.StreamerInfoRepository
import com.example.model.BuildConfig
import com.example.model.StreamerInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamerInfoRepositoryImpl(
    private val streamerInfoRemoteDataSource: StreamerInfoRemoteDataSource
) : StreamerInfoRepository {
    override suspend fun fetchStreamerInfo(): StreamerInfo =
        withContext(Dispatchers.IO) {
            if(BuildConfig.DEBUG){
                StreamerInfo.dummyData()
            }else{
                streamerInfoRemoteDataSource.fetchStreamerInfo()
            }
        }
}