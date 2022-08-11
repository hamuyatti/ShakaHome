package com.example.data.api

import com.example.model.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @Headers(
        "Authorization: Bearer vr3yz2e59jscxrzfvoa6219lwsw2by",
        "Client-Id:x24r8nw8hd6arlvf1hhjuic2n154fl"
    )
    @GET("users")
    suspend fun fetchStreamerBaseInfo(
        @Query("id") streamerId: Int = 49207184
    ): Response<StreamerBaseInfoResponse>

    @Headers(
        "Authorization: Bearer vr3yz2e59jscxrzfvoa6219lwsw2by",
        "Client-Id:x24r8nw8hd6arlvf1hhjuic2n154fl"
    )
    @GET("users/follows")
    suspend fun fetchStreamerFollowInfo(
        @Query("from_id") streamerId: Int = 49207184,
        @Query("after") cursor : FollowInfoPagination? = null
    ): Response<FollowInfoResponse>

    @Headers(
        "Authorization: Bearer vr3yz2e59jscxrzfvoa6219lwsw2by",
        "Client-Id:x24r8nw8hd6arlvf1hhjuic2n154fl"
    )
    @GET("streams")
    suspend fun fetchNowStreamingInfo(
        @Query("user_id") streamerId: Int = 49207184
    ): Response<NowStreamingInfoResponse>

    @Headers(
        "Authorization: Bearer vr3yz2e59jscxrzfvoa6219lwsw2by",
        "Client-Id:x24r8nw8hd6arlvf1hhjuic2n154fl"
    )
    @GET("videos")
    suspend fun fetchVideos(
        @Query("user_id") streamerId: Int = 49207184
    ): Response<PastVideosResponse>

}