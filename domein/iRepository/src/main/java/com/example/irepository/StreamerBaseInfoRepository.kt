package com.example.irepository

import com.example.model.StreamerBaseInfoResponse

interface StreamerBaseInfoRepository {
    suspend fun fetchStreamerBaseInfo(): StreamerBaseInfoResponse
}