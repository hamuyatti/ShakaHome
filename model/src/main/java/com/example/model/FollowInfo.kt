package com.example.model

data class FollowInfo(
    val `data`: List<Data>,
    val pagination: Pagination,
    val total: Int
){
    companion object{
        fun dummyData(): FollowInfo {
            return FollowInfo(

            )
        }
    }
}

data class Data(
    val followed_at: String,
    val from_id: String,
    val from_login: String,
    val from_name: String,
    val to_id: String,
    val to_login: String,
    val to_name: String
)

data class Pagination(
    val cursor: String
)