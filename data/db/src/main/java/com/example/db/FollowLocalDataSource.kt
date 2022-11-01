package com.example.db

import com.example.response.FollowInfoResponse


class FollowLocalDataSource {

    var followInfoCache : FollowInfoResponse? = null
    private set

    fun updateFollowInfoCache(followList : FollowInfoResponse){
        followInfoCache = followList
    }
}