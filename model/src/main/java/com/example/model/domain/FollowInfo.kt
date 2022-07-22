package com.example.model.domain

import com.example.model.response.EachFollowInfo

data class FollowInfo(
    val FollowsInfo: List<EachFollowInfo>,
    val total: String
)
