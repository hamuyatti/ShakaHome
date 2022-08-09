package com.example.core.util

object ThumbnailUrlUtil {
    fun complementSizeForNowStreamingThumbnail(url: String): String {
        val urlComplementedWidth = url.replace("{width}", "1280")
        return urlComplementedWidth.replace("{height}", "720")
    }

    fun complementSizeForPastThumbnail(url: String): String {
        val urlComplementedWidth = url.replace("%{width}", "1280")
        return urlComplementedWidth.replace("%{height}", "720")
    }
}