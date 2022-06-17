package com.example.shakahome.di

import com.example.data.api.StreamerFollowInfoRemoteDataSource
import com.example.data.api.StreamerInfoRemoteDataSource
import com.example.irepository.StreamerFollowInfoRepository
import com.example.repository.StreamerBaseInfoRepositoryImpl
import com.example.irepository.StreamerBaseInfoRepository
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
        remoteDataSource: StreamerInfoRemoteDataSource
    ): StreamerBaseInfoRepository {
        return StreamerBaseInfoRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun providesStreamerFollowInfoRepository(
        remoteDataSource: StreamerFollowInfoRemoteDataSource
    ) : StreamerFollowInfoRepository {
        return StreamerFollowInfoRepositoryImpl(remoteDataSource)
    }
}