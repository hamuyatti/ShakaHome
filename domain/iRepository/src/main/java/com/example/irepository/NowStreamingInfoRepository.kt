package com.example.irepository

import com.example.response.NowStreamingInfoResponse

interface NowStreamingInfoRepository {
    suspend fun fetchNowStreamingInfo(): NowStreamingInfoResponse
}
