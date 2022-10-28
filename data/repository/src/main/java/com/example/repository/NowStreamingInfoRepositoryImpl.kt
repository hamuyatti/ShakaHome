package com.example.repository

import com.example.data.api.NowStreamingInfoDataSource
import com.example.irepository.NowStreamingInfoRepository
import com.example.model.domain.NowStreamingInfo
import com.example.model.response.asDomainModel

class NowStreamingInfoRepositoryImpl(
    private val dataSource: NowStreamingInfoDataSource
) : NowStreamingInfoRepository {
    override suspend fun fetchNowStreamingInfo(): NowStreamingInfo? =
        dataSource.fetchNowStreamingInfo().asDomainModel()
}