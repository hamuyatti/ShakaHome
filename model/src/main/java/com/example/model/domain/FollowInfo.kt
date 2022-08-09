package com.example.model.domain

data class FollowInfo(
    val FollowsInfo: List<EachFollowInfo>,
    val total: String
)

data class EachFollowInfo(
    val followedAt: String,
    val fromId: String,
    val fromLogin: String,
    val fromName: String,
    val toId: String,
    val toLogin: String,
    val toName: String
)
