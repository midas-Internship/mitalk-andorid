package com.example.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.QuestionRepositoryImpl
import com.example.data.repository.RecordRepositoryImpl
import com.example.data.sample.SampleRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.QuestionRepository
import com.example.domain.repository.RecordRepository
import com.example.domain.sample.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideSampleRepository(
        sampleRepositoryImpl: SampleRepositoryImpl,
    ): SampleRepository

    @Binds
    abstract fun provideQuestionRepository(
        questionRepositoryImpl: QuestionRepositoryImpl,
    ): QuestionRepository

    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun provideRecordRepository(
        recordRepositoryImpl: RecordRepositoryImpl
    ): RecordRepository
}
