package com.example.irepository

import com.example.model.StreamerInfo

interface StreamerInfoRepository {
    suspend fun fetchStreamerInfo(): StreamerInfo
}