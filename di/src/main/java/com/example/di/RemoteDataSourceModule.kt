package com.example.di

import com.example.data.remote.datasource.RemoteAuthDataSource
import com.example.data.remote.datasource.RemoteAuthDataSourceImpl
import com.example.data.remote.datasource.RemoteQuestionDataSource
import com.example.data.remote.datasource.RemoteQuestionDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun provideRemoteQuestionDataSource(
        remoteQuestionDataSourceImpl: RemoteQuestionDataSourceImpl
    ): RemoteQuestionDataSource

    @Singleton
    @Binds
    abstract fun provideRemoteAuthDataSource(
        remoteAuthDataSourceImpl: RemoteAuthDataSourceImpl
    ): RemoteAuthDataSource
}