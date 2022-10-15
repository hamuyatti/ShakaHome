package com.example.db

import com.example.model.domain.FollowInfo
import com.example.model.response.FollowInfoPagination

class FollowLocalDataSource {

    var followInfoCache : FollowInfo ? = null
    private set

    fun updateFollowInfoCache(followList : FollowInfo){
        followInfoCache = followList
    }
}