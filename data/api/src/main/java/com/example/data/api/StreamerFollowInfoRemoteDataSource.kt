package com.example.data.api

import com.example.model.response.FollowInfoResponse

class StreamerFollowInfoRemoteDataSource(
    private val api: Api
) {
    suspend fun fetchStreamerFollowInfo(): FollowInfoResponse {
        return api.fetchStreamerFollowInfo().body()!!
    }
}