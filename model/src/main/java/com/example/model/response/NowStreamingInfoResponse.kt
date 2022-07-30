package com.example.model.response

import android.icu.text.MessageFormat.format
import android.os.Build
import android.text.format.DateFormat.format
import androidx.annotation.RequiresApi
import com.example.model.domain.NowStreamingInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.lang.String.format
import java.text.DateFormat
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

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
    @SerialName("tag_ids") val tagIds: List<String>,
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

fun NowStreamingInfoResponse.asDomainModel(): NowStreamingInfo? {
    val info = this.data?.getOrNull(0) ?: return null
    return NowStreamingInfo(
        gameId = info.gameId,
        gameName = info.gameName,
        id = info.id,
        isMature = info.isMature,
        language = info.language,
        startedAt = utcToJtc(info.startedAt),
        tagIds = info.tagIds,
        thumbnailUrl = info.thumbnailUrl,
        title = info.title,
        userId = info.userId,
        userLogin = info.userLogin,
        userName = info.userName,
        viewerCount = info.viewerCount,
        type = info.type
    )
}

private fun utcToJtc(utcTime: String): String {
    val dateTime = OffsetDateTime.parse(utcTime)
    val zoneId = ZoneId.of("Asia/Tokyo")
    val jtc = dateTime.atZoneSameInstant(zoneId).toOffsetDateTime()
    val formatter = DateTimeFormatter.ofPattern("MM月dd日 HH:mm〜")
    return jtc.format(formatter)
}