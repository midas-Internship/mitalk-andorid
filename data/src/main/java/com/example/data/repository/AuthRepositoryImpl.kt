package com.example.data.repository

import android.util.Log
import com.example.data.local.AuthPreference
import com.example.data.local.auth.LocalAuthDataSource
import com.example.data.remote.datasource.RemoteAuthDataSource
import com.example.domain.entity.LoginEntity
import com.example.domain.param.LoginParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteAuthDataSource: RemoteAuthDataSource,
    private val localAuthDataSource: LocalAuthDataSource,
) : AuthRepository {
    override suspend fun login(loginParam: LoginParam) {
        val token = remoteAuthDataSource.login(loginParam)
        localAuthDataSource.saveToken(token)
    }

    override suspend fun autoLogin() {
        val refreshToken = localAuthDataSource.fetchToken().refreshToken
        val token = remoteAuthDataSource.tokenRefresh("Bearer $refreshToken")
        localAuthDataSource.saveToken(token)
    }
}