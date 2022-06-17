package com.example.shakahome.di

import com.example.irepository.StreamerBaseInfoRepository
import com.example.usecase.FetchStreamerInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchStreamerInfoUseCase(
        streamerBaseInfoRepository: StreamerBaseInfoRepository
    ) = FetchStreamerInfoUseCase(streamerBaseInfoRepository)
}