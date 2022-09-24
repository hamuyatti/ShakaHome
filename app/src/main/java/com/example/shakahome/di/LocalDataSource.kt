package com.example.shakahome.di

import com.example.db.FollowLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSource {

    @Provides
    @Singleton
    fun providesLocalFollowDataSource() = FollowLocalDataSource()
}