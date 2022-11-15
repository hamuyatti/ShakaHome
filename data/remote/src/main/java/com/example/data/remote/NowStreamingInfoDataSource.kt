package com.example.data.remote

import com.example.response.NowStreamingInfoResponse


class NowStreamingInfoDataSource(
    private val api: Api
) {
    suspend fun fetchNowStreamingInfo(): NowStreamingInfoResponse {
        return api.fetchNowStreamingInfo().body()!!
    }
}
