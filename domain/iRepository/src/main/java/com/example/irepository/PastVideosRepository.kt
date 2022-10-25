package com.example.irepository

import com.example.model.domain.PastVideosInfo

interface PastVideosRepository {
    suspend fun fetchPastVideos(): PastVideosInfo
}