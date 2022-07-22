package com.example.model.response

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
        FollowsInfo = this.data,
        total = this.total.toString()
    )
}

private fun dummyData(): FollowInfoResponse {
    return FollowInfoResponse(
        total = 163,
        data = listOf(
            EachFollowInfo(
                fromId = "49207184",
                fromLogin = "fps_shaka",
                fromName = "fps_shaka",
                toId = "167077641",
                toLogin = "sovault",
                toName = "ソバルト",
                followedAt = "2022-06-13T02:02:32Z"
            ),
            EachFollowInfo(
                fromId = "49207184",
                fromLogin = "fps_shaka",
                fromName = "fps_shaka",
                toId = "190835892",
                toLogin = "lck_korea",
                toName = "LCK_Korea",
                followedAt = "2022-04-02T12:27:43Z"
            )
        ),
        pagination = FollowInfoPagination(
            cursor = "eyJiIjpudWxsLCJhIjp7IkN1cnNvciI6ImV5SjBjQ0k2SW5WelpYSTZORGt5TURje" +
                    "E9EUTZabTlzYkc5M2N5SXNJblJ6SWpvaWRYTmxjam8wTXpZMk9EZzVNVFVpTENKcGND" +
                    "STZJblZ6WlhJNk5Ea3lNRGN4T0RRNlptOXNiRzkzY3lJc0ltbHpJam9pTVRZME5EZzJ" +
                    "NRGd6TVRjMk1EWXhPREU0TUNKOSJ9fQ"
        )
    )
}