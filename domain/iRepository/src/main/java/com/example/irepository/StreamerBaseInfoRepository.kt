package com.example.irepository

import com.example.model.domain.StreamerBaseInfo

interface StreamerBaseInfoRepository {
    suspend fun fetchStreamerBaseInfo(): StreamerBaseInfo
}