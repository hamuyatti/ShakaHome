package com.example.data.api

import com.example.model.StreamerInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @Headers(
        "Authorization: Bearer 0vvi1ikhyic6c2rq8flw1ukwp0pb2w",
        "Client-Id:x24r8nw8hd6arlvf1hhjuic2n154fl"
    )
    @GET("users")
    fun fetchStreamerInfo(
        @Query("login") StreamerId: Int = 49207184
    ):StreamerInfo
}