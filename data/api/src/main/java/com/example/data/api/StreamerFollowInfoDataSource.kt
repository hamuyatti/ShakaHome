package com.example.data.api

import com.example.model.FollowInfo
import com.example.model.StreamerInfo

class StreamerFollowInfoDataSource(
    private val api: Api
) {
    fun fetchStreamerFollowInfo(): FollowInfo {
        return api.fetchStreamerFollowInfo().body()!!
    }
}