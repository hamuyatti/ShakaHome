package com.example.irepository

import com.example.model.StreamerInfo

interface StreamerInfoRepository {
    fun fetchStreamerInfo(): StreamerInfo
}