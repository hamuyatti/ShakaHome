package com.example.model.domain

data class ReportInfo(
    val nowStreamingInfo: NowStreamingInfo?,
    val pastVideosInfo: PastVideosInfo
) {
    companion object {
        fun from(streamingInfo: NowStreamingInfo?, pastVideosInfo: PastVideosInfo): ReportInfo {
            return ReportInfo(streamingInfo, pastVideosInfo)
        }
    }
}