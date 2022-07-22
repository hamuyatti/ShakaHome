package com.example.irepository

import com.example.model.StreamerBaseInfo

interface StreamerBaseInfoRepository {
    suspend fun fetchStreamerBaseInfo(useDummy: Boolean = false): StreamerBaseInfo
}