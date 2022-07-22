package com.example.model.domain

data class StreamerInfo(
    val baseInfo: StreamerBaseInfo,
    val followInfo: FollowInfo
){
    companion object{
        fun from(baseInfo: StreamerBaseInfo, followInfo: FollowInfo): StreamerInfo {
            return StreamerInfo(baseInfo, followInfo)
        }
    }
}