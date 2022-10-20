package com.example.irepository

import com.example.model.domain.FollowInfo

interface StreamerFollowInfoRepository {
    suspend fun fetchStreamerFollowInfo(): FollowInfo

    suspend fun fetchMoreFollowInfo(nextCursor : String) : FollowInfo
}