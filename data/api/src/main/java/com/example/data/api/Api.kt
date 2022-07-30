package com.example.data.api

import com.example.model.response.FollowInfoResponse
import com.example.model.response.NowStreamingInfoResponse
import com.example.model.response.StreamerBaseInfoResponse
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
        @Query("from_id") streamerId: Int = 49207184
    ): Response<FollowInfoResponse>

    @Headers(
        "Authorization: Bearer vr3yz2e59jscxrzfvoa6219lwsw2by",
        "Client-Id:x24r8nw8hd6arlvf1hhjuic2n154fl"
    )
    @GET("streams")
    suspend fun fetchNowStreamingInfo(
        @Query("user_id") streamerId: Int = 49207184
    ) : Response<NowStreamingInfoResponse>

}