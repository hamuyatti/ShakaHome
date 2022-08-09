package com.example.model.domain

data class PastVideosInfo(
    val pastVideos: List<PastVideo>
)

data class PastVideo(
    val createdAt: String,
    val description: String,
    val duration: String,
    val id: String,
    val language: String,
    val publishedAt: String,
    val streamId: String,
    val thumbnailUrl: String,
    val title: String,
    val type: String,
    val url: String,
    val userId: String,
    val userLogin: String,
    val userName: String,
    val viewCount: Int,
    val viewable: String
)

