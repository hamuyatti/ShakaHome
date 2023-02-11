package com.example.repository

import com.example.core.CoroutineDispatcherProvider
import com.example.data.remote.StreamerInfoRemoteDataSource
import com.example.response.StreamerBaseInfoResponse
import com.example.response.StreamerBaseInfoResponseContent
import com.example.irepository.StreamerBaseInfoRepository
import kotlinx.coroutines.withContext

class StreamerBaseInfoRepositoryImpl(
    private val dataSource: StreamerInfoRemoteDataSource,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : StreamerBaseInfoRepository {
    override suspend fun fetchStreamerBaseInfo(): StreamerBaseInfoResponse {
        return withContext(coroutineDispatcherProvider.io){
            dataSource.fetchStreamerBaseInfo()
        }
    }
}
