package com.example.data.api

import com.example.response.PastVideosResponse

class PastVideosDataSource(
    private val api: Api
) {
    suspend fun fetchPastVideos(): PastVideosResponse {
        return api.fetchVideos().body()!!
    }
}