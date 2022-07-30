package com.example.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PastVideosResponse(
    val `data`: List<PastVideosContent>,
    val pagination: PastVideosPagination
)

@Serializable
data class PastVideosContent(
    val created_at: String,
    val description: String,
    val duration: String,
    val id: String,
    val language: String,
    val muted_segments: List<MutedSegment>,
    val published_at: String,
    val stream_id: String,
    val thumbnail_url: String,
    val title: String,
    val type: String,
    val url: String,
    val user_id: String,
    val user_login: String,
    val user_name: String,
    val view_count: Int,
    val viewable: String
)

@Serializable
data class MutedSegment(
    val duration: Int,
    val offset: Int
)

@Serializable
data class PastVideosPagination(
    val cursor: String
)
