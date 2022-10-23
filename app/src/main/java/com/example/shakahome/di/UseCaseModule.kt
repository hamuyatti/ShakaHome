package com.example.shakahome.di

import com.example.irepository.NowStreamingInfoRepository
import com.example.irepository.PastVideosRepository
import com.example.irepository.StreamerBaseInfoRepository
import com.example.irepository.StreamerFollowInfoRepository
import com.example.usecase.*
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
    ) = FetchStreamerBaseInfoUseCase(streamerBaseInfoRepository)

    @Provides
    @Singleton
    fun providesFetchFollowInfoUseCase(
        followInfoRepository: StreamerFollowInfoRepository
    ) = FetchFollowInfoUseCase(followInfoRepository)

    @Provides
    @Singleton
    fun providesFetchMoreFollowInfoUseCase(
        followInfoRepository: StreamerFollowInfoRepository
    ) = FetchMoreFollowInfoUseCase(followInfoRepository)

    @Provides
    @Singleton
    fun providesFetchNowStreamingInfoUseCase(
        nowStreamingInfoRepository: NowStreamingInfoRepository,
    ) = FetchNowStreamingInfoUseCase(nowStreamingInfoRepository)

    @Provides
    @Singleton
    fun providesFetchPastVideosUseCase(
        pastVideosRepository: PastVideosRepository
    ) = FetchPastVideosUseCase(pastVideosRepository)

    @Provides
    @Singleton
    fun sortFollowListUseCase() = SortFollowListUseCase()


}
