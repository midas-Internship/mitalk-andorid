package com.example.data.local.auth

import com.example.domain.entity.LoginEntity

interface LocalAuthDataSource {

    suspend fun fetchToken(): LoginEntity

    suspend fun saveToken(loginEntity: LoginEntity)

    suspend fun clearToken()
}