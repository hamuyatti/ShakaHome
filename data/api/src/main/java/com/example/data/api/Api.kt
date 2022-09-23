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
        @Query("first") first: Int = 100
    ): Response<FollowInfoResponse>

    @Headers(
        "Authorization: Bearer vr3yz2e59jscxrzfvoa6219lwsw2by",
        "Client-Id:x24r8nw8hd6arlvf1hhjuic2n154fl"
    )
    @GET("users/follows")
    suspend fun fetchStreamerFollowInfoWithCursor(
        @Query("from_id") streamerId: Int = 49207184,
        @Query("first") first: Int = 100,
        @Query("after") after: String = "eyJiIjpudWxsLCJhIjp7IkN1cnNvciI6ImV5SjBjQ0k2SW5WelpYSTZORGt5TURjeE9EUTZabTlzYkc5M2N5SXNJblJ6SWpvaWRYTmxjam94TWpFMU1UQXlNellpTENKcGNDSTZJblZ6WlhJNk5Ea3lNRGN4T0RRNlptOXNiRzkzY3lJc0ltbHpJam9pTVRZME56RXlORGMzTVRZM05UQXhOek16T1NKOSJ9fQ"
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