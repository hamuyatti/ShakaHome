package com.example.entity

import com.example.response.StreamerBaseInfoResponse

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
) {
    companion object {
        fun from(streamerBaseInfoResponse: StreamerBaseInfoResponse): StreamerBaseInfo? {
            val info = streamerBaseInfoResponse.data.getOrNull(0) ?: return null
            return StreamerBaseInfo(
                id = info.id,
                login = info.login,
                displayName = info.displayName,
                type = info.type,
                broadcasterType = info.broadcasterType,
                description = info.description,
                profileImageUrl = info.profileImageUrl,
                offlineImageUrl = info.offlineImageUrl,
                viewCount = info.viewCount,
                createdAt = info.createdAt
            )
        }
    }
}