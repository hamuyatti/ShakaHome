package com.example.irepository

import com.example.response.StreamerBaseInfoResponse

interface StreamerBaseInfoRepository {
    suspend fun fetchStreamerBaseInfo(): StreamerBaseInfoResponse
}