package com.example.di

import com.example.data.repository.*
import com.example.data.sample.SampleRepositoryImpl
import com.example.domain.repository.*
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

    @Binds
    abstract fun provideReviewRepository(
        reviewRepositoryImpl: ReviewRepositoryImpl
    ): ReviewRepository

    @Binds
    abstract fun provideChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository

    @Binds
    abstract fun provideFileRepository(
        fileRepositoryImpl: FileRepositoryImpl
    ): FileRepository

    @Binds
    abstract fun provideSystemRepository(
        systemRepositoryImpl: SystemRepositoryImpl
    ): SystemRepository
}
