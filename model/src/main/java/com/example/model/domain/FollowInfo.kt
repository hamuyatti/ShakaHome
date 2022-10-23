package com.example.model.domain

data class FollowInfo(
    val followsInfo: List<EachFollowInfo>,
    val total: String,
    val cursor: String? = null
)

data class EachFollowInfo(
    val followedAt: String,
    val fromId: String,
    val fromLogin: String,
    val fromName: String,
    val toId: String,
    val toLogin: String,
    val toName: String,
    val index: Int
)
