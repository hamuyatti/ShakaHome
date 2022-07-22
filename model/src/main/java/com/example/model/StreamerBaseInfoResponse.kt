package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class StreamerBaseInfoResponse(
    val `data`: List<StreamerBaseInfo>
)

fun StreamerBaseInfoResponse.asDomainModel(): StreamerBaseInfo {
    val info = this.data[0]
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