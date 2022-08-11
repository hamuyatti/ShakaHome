package com.example.db

import com.example.model.domain.EachFollowInfo

class FollowLocalDataSource {

    var followInfoCache : List<EachFollowInfo> ? = null
    private set


    fun updateFollowInfoCache(followList : List<EachFollowInfo>){
        followInfoCache = followList
    }
}