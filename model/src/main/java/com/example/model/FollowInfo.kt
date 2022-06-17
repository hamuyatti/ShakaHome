package com.example.model

data class FollowInfo(
    val `data`: List<Data>,
    val pagination: Pagination,
    val total: Int
) {
    companion object {
        fun dummyData(): FollowInfo = dummyDate()
    }
}

data class Data(
    val followedAt: String,
    val fromId: String,
    val fromLogin: String,
    val fromName: String,
    val toId: String,
    val toLogin: String,
    val toName: String
)

data class Pagination(
    val cursor: String
)

private fun dummyDate(): FollowInfo {
    return FollowInfo(
        total = 163,
        data = listOf(
            Data(
                fromId = "49207184",
                fromLogin = "fps_shaka",
                fromName = "fps_shaka",
                toId = "167077641",
                toLogin = "sovault",
                toName = "ソバルト",
                followedAt = "2022-06-13T02:02:32Z"
            ),
            Data(
                fromId = "49207184",
                fromLogin = "fps_shaka",
                fromName = "fps_shaka",
                toId = "190835892",
                toLogin = "lck_korea",
                toName = "LCK_Korea",
                followedAt = "2022-04-02T12:27:43Z"
            )
        ),
        pagination = Pagination(
            cursor = "eyJiIjpudWxsLCJhIjp7IkN1cnNvciI6ImV5SjBjQ0k2SW5WelpYSTZORGt5TURje" +
                    "E9EUTZabTlzYkc5M2N5SXNJblJ6SWpvaWRYTmxjam8wTXpZMk9EZzVNVFVpTENKcGND" +
                    "STZJblZ6WlhJNk5Ea3lNRGN4T0RRNlptOXNiRzkzY3lJc0ltbHpJam9pTVRZME5EZzJ" +
                    "NRGd6TVRjMk1EWXhPREU0TUNKOSJ9fQ"
        )
    )
}