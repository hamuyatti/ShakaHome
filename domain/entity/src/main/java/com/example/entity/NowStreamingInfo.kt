package com.example.entity

import com.example.core.util.DateUtil
import com.example.core.util.ThumbnailUrlUtil.complementSizeForNowStreamingThumbnail
import com.example.response.NowStreamingInfoResponse

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
) {
    companion object {
        fun from(nowStreamingInfoResponse: NowStreamingInfoResponse): NowStreamingInfo? {
            val info = nowStreamingInfoResponse.data?.getOrNull(0) ?: return null
            return NowStreamingInfo(
                gameId = info.gameId,
                gameName = info.gameName,
                id = info.id,
                isMature = info.isMature,
                language = info.language,
                startedAt = "${DateUtil.utcToJtc(info.startedAt)}~",
                tagIds = info.tagIds ?: listOf(),
                thumbnailUrl = complementSizeForNowStreamingThumbnail(info.thumbnailUrl),
                title = info.title,
                userId = info.userId,
                userLogin = info.userLogin,
                userName = info.userName,
                viewerCount = info.viewerCount,
                type = info.type
            )
        }
    }
}
