package com.example.shakahome.di

import com.example.core.CoroutineDispatcherProvider
import com.example.data.remote.NowStreamingInfoDataSource
import com.example.data.remote.PastVideosDataSource
import com.example.data.remote.StreamerFollowInfoRemoteDataSource
import com.example.data.remote.StreamerInfoRemoteDataSource
import com.example.local.FollowLocalDataSource
import com.example.irepository.NowStreamingInfoRepository
import com.example.irepository.PastVideosRepository
import com.example.irepository.StreamerFollowInfoRepository
import com.example.repository.StreamerBaseInfoRepositoryImpl
import com.example.irepository.StreamerBaseInfoRepository
import com.example.repository.NowStreamingInfoRepositoryImpl
import com.example.repository.PastVideosRepositoryImpl
import com.example.repository.StreamerFollowInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideStreamerInfoRepository(
        remoteDataSource: StreamerInfoRemoteDataSource,
        coroutineDispatcherProvider: CoroutineDispatcherProvider
    ): StreamerBaseInfoRepository {
        return StreamerBaseInfoRepositoryImpl(
            dataSource = remoteDataSource,
            coroutineDispatcherProvider = coroutineDispatcherProvider
        )
    }

    @Provides
    @Singleton
    fun providesStreamerFollowInfoRepository(
        remoteDataSource: StreamerFollowInfoRemoteDataSource,
        localDataSource: FollowLocalDataSource,
        coroutineDispatcherProvider: CoroutineDispatcherProvider
    ): StreamerFollowInfoRepository {
        return StreamerFollowInfoRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            coroutineDispatcherProvider = coroutineDispatcherProvider
        )
    }

    @Provides
    @Singleton
    fun providesNowStreamingInfoRepository(
        remoteDataSource: NowStreamingInfoDataSource,
        coroutineDispatcherProvider: CoroutineDispatcherProvider
    ): NowStreamingInfoRepository {
        return NowStreamingInfoRepositoryImpl(
            dataSource = remoteDataSource,
            coroutineDispatcher = coroutineDispatcherProvider
        )
    }

    @Provides
    @Singleton
    fun providesPastVideosRepository(
        dataSource: PastVideosDataSource,
        coroutineDispatcherProvider: CoroutineDispatcherProvider
    ): PastVideosRepository {
        return PastVideosRepositoryImpl(
            dataSource = dataSource,
            coroutineDispatcherProvider = coroutineDispatcherProvider
        )
    }
}