package com.example.shakahome.di

import com.example.data.api.Api
import com.example.data.api.StreamerInfoRemoteDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val format = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.twitch.tv/helix/")
            .addConverterFactory(format.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(
        retrofit: Retrofit
    ): Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun providesStreamerInfoDataSource(
        api: Api
    ) = StreamerInfoRemoteDataSource(api = api)
}