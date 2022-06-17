package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class StreamerBaseInfo(
    val id: Int,
    val login: String,
    val displayName: String,
    val type: String,
    val broadcasterType: String,
    val description: String,
    val profileImageUrl: String,
    val offlineImageUrl: String,
    val viewCount: Int,
    val createdAt: String
) {
    companion object{
        fun dummyData(): StreamerBaseInfo {
            return StreamerBaseInfo(
                id = 49207184,
                login = "fps_shaka",
                displayName = "fps_shaka",
                type = "",
                broadcasterType = "partner",
                description = "",
                profileImageUrl = "https://static-cdn.jtvnw.net/jtv_user_pictures/61f568bf-884b-4126-b17c-fc525c6d3bd4-profile_image-300x300.png",
                offlineImageUrl ="https://static-cdn.jtvnw.net/jtv_user_pictures/282d883a-8e00-4fd3-88fa-bfcbd370c2cd-channel_offline_image-1920x1080.jpeg",
                viewCount =135686238,
                createdAt ="2013-09-19T13:21:29Z"
            )
        }
    }
}