package com.example.data.api

import com.example.model.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val authorization = "Authorization: Bearer ig8t6eujfxl5swgszhrtlbhs9eecsh"
const val clientId = "Client-Id:x24r8nw8hd6arlvf1hhjuic2n154fl"

interface Api {

    @Headers(
        authorization,
        clientId
    )
    @GET("users")
    suspend fun fetchStreamerBaseInfo(
        @Query("id") streamerId: Int = 49207184
    ): Response<StreamerBaseInfoResponse>

    @Headers(
        authorization,
        clientId
    )
    @GET("users/follows")
    suspend fun fetchStreamerFollowInfo(
        @Query("from_id") streamerId: Int = 49207184,
        @Query("first") first: Int = 100
    ): Response<FollowInfoResponse>

    @Headers(
        authorization,
        clientId
    )
    @GET("users/follows")
    suspend fun fetchStreamerFollowInfoWithCursor(
        @Query("from_id") streamerId: Int = 49207184,
        @Query("first") first: Int = 100,
        @Query("after") after: String
    ): Response<FollowInfoResponse>

    @Headers(
        authorization,
        clientId
    )
    @GET("streams")
    suspend fun fetchNowStreamingInfo(
        @Query("user_id") streamerId: Int = 49207184
    ): Response<NowStreamingInfoResponse>

    @Headers(
        authorization,
        clientId
    )
    @GET("videos")
    suspend fun fetchVideos(
        @Query("user_id") streamerId: Int = 49207184
    ): Response<PastVideosResponse>

}