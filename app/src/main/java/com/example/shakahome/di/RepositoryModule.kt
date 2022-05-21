package com.example.shakahome.di

import com.example.data.api.StreamerInfoRemoteDataSource
import com.example.repository.StreamerInfoRepositoryImpl
import com.example.irepository.StreamerInfoRepository
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
    ): StreamerInfoRepository {
        return StreamerInfoRepositoryImpl(remoteDataSource)
    }
}