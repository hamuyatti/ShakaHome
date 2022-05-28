package com.example.shakahome.di

import com.example.irepository.StreamerInfoRepository
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
        streamerInfoRepository: StreamerInfoRepository
    ) = FetchStreamerInfoUseCase(streamerInfoRepository)
}