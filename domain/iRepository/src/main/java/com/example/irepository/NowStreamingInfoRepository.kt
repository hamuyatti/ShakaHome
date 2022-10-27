package com.example.irepository

import com.example.model.domain.NowStreamingInfo

interface NowStreamingInfoRepository {
    suspend fun fetchNowStreamingInfo(): NowStreamingInfo?
}
