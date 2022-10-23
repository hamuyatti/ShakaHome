package com.example.model.domain

import java.util.Date

data class FollowInfo(
    val followsList: List<EachFollowInfo>,
    val total: String,
    val cursor: String? = null
)

data class EachFollowInfo(
    val followedAt: Date,
    val fromId: String,
    val fromLogin: String,
    val fromName: String,
    val toId: String,
    val toLogin: String,
    val toName: String
)
