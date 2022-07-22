package com.example.irepository

import com.example.model.FollowInfoResponse

interface StreamerFollowInfoRepository {
    suspend fun fetchStreamerFollowInfo(useDummy: Boolean = false): FollowInfoResponse
}