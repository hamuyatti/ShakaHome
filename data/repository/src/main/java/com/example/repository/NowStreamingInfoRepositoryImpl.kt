package com.example.repository

import com.example.core.CoroutineDispatcherProvider
import com.example.data.remote.NowStreamingInfoDataSource
import com.example.response.NowStreamingInfoContent
import com.example.response.NowStreamingInfoResponse
import com.example.irepository.NowStreamingInfoRepository
import kotlinx.coroutines.CloseableCoroutineDispatcher
import kotlinx.coroutines.withContext

class NowStreamingInfoRepositoryImpl(
    private val dataSource: NowStreamingInfoDataSource,
    private val coroutineDispatcher: CoroutineDispatcherProvider
) : NowStreamingInfoRepository {
    override suspend fun fetchNowStreamingInfo(): NowStreamingInfoResponse {
        return withContext(coroutineDispatcher.io) {
            dataSource.fetchNowStreamingInfo()
        }
    }
}
