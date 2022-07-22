package com.example.model.domain

data class NowStreamingInfo(
    val gameId: String,
    val gameName: String,
    val id: String,
    val isMature: Boolean,
    val language: String,
    val startedAt: String,
    val tagIds: List<String>,
    val thumbnailUrl: String,
    val title: String,
    val type: String,
    val userId: String,
    val userLogin: String,
    val userName: String,
    val viewerCount: Int
)
