package com.example.di

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

}