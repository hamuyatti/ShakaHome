package com.example.shakahome.di

import com.example.irepository.NowStreamingInfoRepository
import com.example.irepository.StreamerBaseInfoRepository
import com.example.irepository.StreamerFollowInfoRepository
import com.example.usecase.FetchNowStreamingInfoUseCase
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
        streamerBaseInfoRepository: StreamerBaseInfoRepository,
        streamerFollowInfoRepository: StreamerFollowInfoRepository
    ) = FetchStreamerInfoUseCase(streamerBaseInfoRepository, streamerFollowInfoRepository)

    @Provides
    @Singleton
    fun providesFetchNowStreamingInfoUseCase(
        nowStreamingInfoRepository: NowStreamingInfoRepository
    ) = FetchNowStreamingInfoUseCase(nowStreamingInfoRepository)
}
