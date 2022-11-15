package com.example.repository

import com.example.data.remote.PastVideosDataSource
import com.example.response.PastVideosResponse
import com.example.irepository.PastVideosRepository

class PastVideosRepositoryImpl(
    private val dataSource: PastVideosDataSource
) : PastVideosRepository {
    override suspend fun fetchPastVideos(): PastVideosResponse =
        dataSource.fetchPastVideos()
}