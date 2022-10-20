package com.example.irepository

import com.example.model.response.StreamerBaseInfoResponse


interface StreamerBaseInfoRepository {
    suspend fun fetchStreamerBaseInfo(): StreamerBaseInfoResponse
}