package com.example.irepository

import com.example.response.PastVideosResponse

interface PastVideosRepository {
    suspend fun fetchPastVideos(): PastVideosResponse
}