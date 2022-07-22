package com.example.data.api

import com.example.model.response.StreamerBaseInfoResponse


class StreamerInfoRemoteDataSource(
    private val api: Api
) {
    suspend fun fetchStreamerBaseInfo(): StreamerBaseInfoResponse {
        return api.fetchStreamerBaseInfo().body()!!
    }
}