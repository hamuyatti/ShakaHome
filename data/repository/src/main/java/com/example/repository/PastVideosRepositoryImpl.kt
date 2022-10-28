package com.example.repository

import com.example.data.api.PastVideosDataSource
import com.example.irepository.PastVideosRepository
import com.example.model.domain.PastVideosInfo
import com.example.model.response.asDomainModel

class PastVideosRepositoryImpl(
    private val dataSource: PastVideosDataSource
) : PastVideosRepository {
    override suspend fun fetchPastVideos(): PastVideosInfo =
        dataSource.fetchPastVideos().asDomainModel()
}