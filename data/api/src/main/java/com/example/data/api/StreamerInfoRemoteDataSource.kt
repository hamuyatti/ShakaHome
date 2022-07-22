package com.example.data.api

import com.example.model.StreamerBaseInfo

class StreamerInfoRemoteDataSource(
    private val api: Api
) {
    suspend fun fetchStreamerBaseInfo(): StreamerBaseInfo {
        return api.fetchStreamerBaseInfo().body()!!
    }
}