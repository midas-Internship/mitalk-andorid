package com.example.data.local

import java.time.LocalDateTime
import java.time.ZonedDateTime

interface AuthPreference {

    suspend fun saveAccessToken(accessToken: String)

    suspend fun fetchAccessToken(): String

    suspend fun clearAccessToken()

    suspend fun saveRefreshToken(refreshToken: String)

    suspend fun fetchRefreshToken(): String

    suspend fun clearRefreshToken()

    suspend fun saveRefreshExp(refreshExp: ZonedDateTime)

    suspend fun fetchRefreshExp(): ZonedDateTime

    suspend fun clearRefreshExp()
}