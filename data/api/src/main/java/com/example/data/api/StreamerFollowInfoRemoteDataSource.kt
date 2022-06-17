package com.example.data.api

import com.example.model.FollowInfo

class StreamerFollowInfoRemoteDataSource(
    private val api: Api
) {
    fun fetchStreamerFollowInfo(): FollowInfo {
        return api.fetchStreamerFollowInfo().body()!!
    }
}