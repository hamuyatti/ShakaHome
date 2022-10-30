package com.example.irepository

import com.example.response.FollowInfoResponse

interface StreamerFollowInfoRepository {
    suspend fun fetchStreamerFollowInfo(): FollowInfoResponse

    suspend fun fetchMoreFollowInfo(nextCursor : String) : FollowInfoResponse
}