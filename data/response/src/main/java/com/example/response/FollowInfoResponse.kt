package com.example.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowInfoResponse(
    val `data`: List<EachFollowInfoResponse>,
    val pagination: FollowInfoPagination? = null,
    val total: Int
)

@Serializable
data class EachFollowInfoResponse(
    @SerialName("followed_at") val followedAt: String,
    @SerialName("from_id") val fromId: String,
    @SerialName("from_login") val fromLogin: String,
    @SerialName("from_name") val fromName: String,
    @SerialName("to_id") val toId: String,
    @SerialName("to_login") val toLogin: String,
    @SerialName("to_name") val toName: String
)

@Serializable
data class FollowInfoPagination(
    val cursor: String? = null
)