package com.example.data.api

import com.example.model.StreamerInfo
import retrofit2.Response

class StreamerInfoRemoteDataSource(
    private val api: Api
) {
    fun fetchStreamerInfo(): StreamerInfo {
        return api.fetchStreamerInfo().body()!!
    }
}