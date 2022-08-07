package com.example.model.response

import com.example.core.util.DateUtil.utcToJtc
import com.example.model.domain.FollowInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowInfoResponse(
    val `data`: List<EachFollowInfo>,
    val pagination: FollowInfoPagination,
    val total: Int
)

@Serializable
data class EachFollowInfo(
    @SerialName("followed_at") val followedAt: String,
    @SerialName("from_id") val fromId: String,
    @SerialName("from_login") val fromLogin: String,
    @SerialName("from_name") val fromName: String,
    @SerialName("to_id") val toId: String,
    @SerialName("to_login") val toLogin: String,
    @SerialName("to_name") val toName: String
)

@Serializable
data class FollowInfoPagination(
    val cursor: String
)

fun FollowInfoResponse.asDomainModel(): FollowInfo {
    return FollowInfo(
        FollowsInfo = this.data.map {
            it.copy(
                followedAt = utcToJtc(it.followedAt)
            )
        },
        total = this.total.toString()
    )
}