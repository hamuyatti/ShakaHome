package com.example.data.remote

import com.example.response.StreamerBaseInfoResponse


class StreamerInfoRemoteDataSource(
    private val api: Api
) {
    suspend fun fetchStreamerBaseInfo(): StreamerBaseInfoResponse {
        return api.fetchStreamerBaseInfo().body()!!
    }
}