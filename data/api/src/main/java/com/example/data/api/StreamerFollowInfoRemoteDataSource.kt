package com.example.data.api

import com.example.model.response.FollowInfoPagination
import com.example.model.response.FollowInfoResponse

class StreamerFollowInfoRemoteDataSource(
    private val api: Api
) {
    suspend fun fetchStreamerFollowInfo(nextCursor: FollowInfoPagination? = null): FollowInfoResponse {
        return api.fetchStreamerFollowInfo(cursor = nextCursor).body()!!
    }
}