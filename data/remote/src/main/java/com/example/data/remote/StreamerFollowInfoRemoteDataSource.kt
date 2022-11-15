package com.example.data.remote

import com.example.response.FollowInfoResponse


class StreamerFollowInfoRemoteDataSource(
    private val api: Api
) {
    suspend fun fetchStreamerFollowInfo(): FollowInfoResponse {
        return api.fetchStreamerFollowInfo().body()!!
    }

    suspend fun fetchStreamerMoreFollowInfo(nextCursor: String): FollowInfoResponse {
        return api.fetchStreamerFollowInfoWithCursor(after = nextCursor).body()!!
    }
}