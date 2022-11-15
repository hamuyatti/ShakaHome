package com.example.local

import com.example.response.FollowInfoResponse


class FollowLocalDataSource {

    var followInfoCache : FollowInfoResponse? = null
    private set

    fun updateFollowInfoCache(followList : FollowInfoResponse){
        followInfoCache = followList
    }
}