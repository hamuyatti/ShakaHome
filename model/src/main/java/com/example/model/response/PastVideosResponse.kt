package com.example.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PastVideosResponse(
    val `data`: List<PastVideosContent>,
    val pagination: PastVideosPagination
)

@Serializable
data class PastVideosContent(
    @SerialName("created_at") val createdAt: String,
    val description: String,
    val duration: String,
    val id: String,
    val language: String,
    @SerialName("muted_segments") val mutedSegments: List<MutedSegment>,
    @SerialName("published_a") val publishedAt: String,
    @SerialName("stream_id") val streamId: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    val title: String,
    val type: String,
    val url: String,
    @SerialName("user_id") val userId: String,
    @SerialName("user_login") val userLogin: String,
    @SerialName("user_name") val userName: String,
    @SerialName("view_count") val viewCount: Int,
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
