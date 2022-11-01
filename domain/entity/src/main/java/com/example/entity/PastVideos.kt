package com.example.entity

import com.example.core.util.DateUtil.format
import com.example.core.util.DateUtil.utcToJtc
import com.example.core.util.ThumbnailUrlUtil.complementSizeForPastThumbnail
import com.example.response.PastVideosResponse

data class PastVideosInfo(
    val pastVideos: List<PastVideo>
) {
    companion object {
        fun from(pastVideosResponse: PastVideosResponse): PastVideosInfo {
            return PastVideosInfo(
                pastVideos = pastVideosResponse.data.map {
                    PastVideo(
                        createdAt = utcToJtc(it.createdAt),
                        description = it.description,
                        duration = format(it.duration),
                        id = it.id,
                        language = it.language,
                        publishedAt = it.publishedAt,
                        streamId = it.streamId,
                        thumbnailUrl = complementSizeForPastThumbnail(it.thumbnailUrl),
                        title = it.title,
                        type = it.type,
                        url = it.url,
                        userId = it.userId,
                        userLogin = it.userLogin,
                        userName = it.userName,
                        viewCount = it.viewCount,
                        viewable = it.viewable
                    )
                }
            )
        }
    }
}

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
