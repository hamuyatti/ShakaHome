package com.example.repository

import com.example.data.remote.NowStreamingInfoDataSource
import com.example.response.NowStreamingInfoContent
import com.example.response.NowStreamingInfoResponse
import com.example.irepository.NowStreamingInfoRepository

class NowStreamingInfoRepositoryImpl(
    private val dataSource: NowStreamingInfoDataSource
) : NowStreamingInfoRepository {
    override suspend fun fetchNowStreamingInfo(): NowStreamingInfoResponse {
        return dataSource.fetchNowStreamingInfo()
    }
}