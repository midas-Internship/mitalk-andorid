package com.example.di

import com.example.data.local.auth.LocalAuthDataSource
import com.example.data.local.auth.LocalAuthDataSourceImpl
import com.example.data.local.chat.LocalChatDataSource
import com.example.data.local.chat.LocalChatDataSourceImpl
import com.example.data.local.system.LocalSystemDataSource
import com.example.data.local.system.LocalSystemDataSourceImpl
import com.example.data.sample.SampleDataSource
import com.example.data.sample.SampleDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Singleton
    @Binds
    abstract fun provideSampleDataSource(
        sampleDataSourceImpl: SampleDataSourceImpl
    ): SampleDataSource

    @Singleton
    @Binds
    abstract fun provideAuthDataSource(
        localAuthDataSourceImpl: LocalAuthDataSourceImpl
    ): LocalAuthDataSource

    @Singleton
    @Binds
    abstract fun provideChatDataSource(
        localChatDataSourceImpl: LocalChatDataSourceImpl
    ): LocalChatDataSource

    @Singleton
    @Binds
    abstract fun provideSystemDataSource(
        localSystemDataSourceImpl: LocalSystemDataSourceImpl
    ): LocalSystemDataSource
}