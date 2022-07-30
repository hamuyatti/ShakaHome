package com.example.irepository

import com.example.model.response.PastVideosResponse

interface PastVideosRepository {
    suspend fun fetchPastVideos(): PastVideosResponse
}