package com.example.irepository

import com.example.model.response.NowStreamingInfoResponse

interface NowStreamingInfoRepository {
    suspend fun fetchNowStreamingInfo(): NowStreamingInfoResponse
}
