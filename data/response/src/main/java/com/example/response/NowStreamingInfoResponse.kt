package com.example.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowStreamingInfoResponse(
    val `data`: List<NowStreamingInfoContent>?,
    val pagination: NowStreamingInfoPagination? = null
)

@Serializable
data class NowStreamingInfoContent(
    @SerialName("game_id") val gameId: String,
    @SerialName("game_name") val gameName: String,
    val id: String,
    @SerialName("is_mature") val isMature: Boolean,
    val language: String,
    @SerialName("started_at") val startedAt: String,
    @SerialName("tag_ids") val tagIds: List<String>?,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    val title: String,
    val type: String,
    @SerialName("user_id") val userId: String,
    @SerialName("user_login") val userLogin: String,
    @SerialName("user_name") val userName: String,
    @SerialName("viewer_count") val viewerCount: Int
)

@Serializable
data class NowStreamingInfoPagination(
    val cursor: String? = null
)