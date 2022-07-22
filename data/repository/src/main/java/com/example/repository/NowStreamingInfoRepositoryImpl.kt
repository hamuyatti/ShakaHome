package com.example.repository

import com.example.data.api.NowStreamingInfoDataSource
import com.example.irepository.NowStreamingInfoRepository
import com.example.model.response.NowStreamingInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NowStreamingInfoRepositoryImpl(
    private val dataSource: NowStreamingInfoDataSource
) : NowStreamingInfoRepository {
    override suspend fun fetchNowStreamingInfo(): NowStreamingInfoResponse =
        withContext(Dispatchers.IO){
            dataSource.fetchNowStreamingInfo()
    }
}