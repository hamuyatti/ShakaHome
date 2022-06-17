package com.example.data.api

import com.example.model.StreamerBaseInfo

class StreamerBaseInfoRemoteDataSource(
    private val api: Api
) {
    fun fetchStreamerBaseInfo(): StreamerBaseInfo {
        return api.fetchStreamerBaseInfo().body()!!
    }
}