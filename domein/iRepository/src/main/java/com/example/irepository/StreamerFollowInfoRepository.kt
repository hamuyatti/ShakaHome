package com.example.irepository

import com.example.model.FollowInfo

interface StreamerFollowInfoRepository {
    suspend fun fetchStreamerFollowInfo() : FollowInfo
}