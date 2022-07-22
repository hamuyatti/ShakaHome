package com.example.irepository

import com.example.model.response.FollowInfoResponse

interface StreamerFollowInfoRepository {
    suspend fun fetchStreamerFollowInfo(): FollowInfoResponse
}