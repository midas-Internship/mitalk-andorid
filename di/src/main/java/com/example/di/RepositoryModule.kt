package com.example.di

import com.example.data.sample.SampleRepositoryImpl
import com.example.domain.sample.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideSampleRepository(
        sampleRepositoryImpl: SampleRepositoryImpl,
    ): SampleRepository
}
