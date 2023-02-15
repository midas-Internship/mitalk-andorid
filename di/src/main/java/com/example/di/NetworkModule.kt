package com.example.di

import android.util.Log
import com.example.data.remote.api.AuthApi
import com.example.data.remote.api.QuestionApi
import com.example.data.remote.api.RecordApi
import com.example.data.remote.api.ReviewApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Log.v("HTTP", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOkHttpclient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideQuestionApi(
        retrofit: Retrofit
    ): QuestionApi =
        retrofit.create(QuestionApi::class.java)

    @Provides
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    fun provideRecordApi(
        retrofit: Retrofit
    ): RecordApi =
        retrofit.create(RecordApi::class.java)

    @Provides
    fun provideReviewApi(
        retrofit: Retrofit
    ): ReviewApi =
        retrofit.create(ReviewApi::class.java)
}