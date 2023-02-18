package com.example.repository

import com.example.core.CoroutineDispatcherProvider
import com.example.data.remote.PastVideosDataSource
import com.example.response.PastVideosResponse
import com.example.irepository.PastVideosRepository
import kotlinx.coroutines.withContext

class PastVideosRepositoryImpl(
    private val dataSource: PastVideosDataSource,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : PastVideosRepository {
    override suspend fun fetchPastVideos(): PastVideosResponse =
        withContext(coroutineDispatcherProvider.io){
            dataSource.fetchPastVideos()
        }
}