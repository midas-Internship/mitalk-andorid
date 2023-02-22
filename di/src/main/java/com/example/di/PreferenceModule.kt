package com.example.di

import com.example.data.local.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun bindAuthPreference(
        authPreferenceImpl: AuthPreferenceImpl
    ): AuthPreference

    @Binds
    abstract fun bindChatPreference(
        chatPreferenceImpl: ChatPreferenceImpl
    ): ChatPreference

    @Binds
    abstract fun bindSystemPreference(
        systemPreferenceImpl: SystemPreferenceImpl
    ): SystemPreference
}