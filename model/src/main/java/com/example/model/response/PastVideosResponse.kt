package com.example.model.response

import com.example.core.util.DateUtil.format
import com.example.core.util.DateUtil.utcToDate
import com.example.core.util.DateUtil.utcToJtc
import com.example.core.util.ThumbnailUrlUtil.complementSizeForPastThumbnail
import com.example.model.domain.PastVideo
import com.example.model.domain.PastVideosInfo
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
    @SerialName("muted_segments") val mutedSegments: List<MutedSegment>?,
    @SerialName("published_at") val publishedAt: String,
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


fun PastVideosResponse.asDomainModel(): PastVideosInfo {
    return PastVideosInfo(
        pastVideos = this.data.map {
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
