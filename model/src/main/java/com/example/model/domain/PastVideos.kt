package com.example.model.domain

import com.example.model.response.PastVideosContent
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class PastVideosInfo(
    val pastVideos: List<PastVideosContent>
) {
    companion object {
        fun utcToJtc(utcTime: String): String {
            val dateTime = OffsetDateTime.parse(utcTime)
            val zoneId = ZoneId.of("Asia/Tokyo")
            val jtc = dateTime.atZoneSameInstant(zoneId).toOffsetDateTime()
            val formatter = DateTimeFormatter.ofPattern("MM月dd日 HH:mm")
            return jtc.format(formatter)
        }

        fun format(time: String): String {
            val a = time.replace("h", "時間")
            val b = a.replace("m", "分")
            return b.replace("s", "秒")
        }

        fun complementSizeForNowStreamingThumbnail(url: String): String {
            val urlComplementedWidth = url.replace("{width}", "1280")
            return urlComplementedWidth.replace("{height}", "720")
        }

        fun complementSizeForPastThumbnail(url: String): String {
            val urlComplementedWidth = url.replace("%{width}", "1280")
            return urlComplementedWidth.replace("%{height}", "720")
        }
    }
}
