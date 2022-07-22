package com.example.model.domain

data class StreamerBaseInfo(
    val id: Int,
    val login: String,
    val displayName: String,
    val type: String,
    val broadcasterType: String,
    val description: String,
    val profileImageUrl: String,
    val offlineImageUrl: String,
    val viewCount: Int,
    val createdAt: String
)