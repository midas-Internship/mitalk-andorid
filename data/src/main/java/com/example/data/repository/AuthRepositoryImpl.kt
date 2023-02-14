package com.example.data.repository

import com.example.data.remote.datasource.RemoteAuthDataSource
import com.example.domain.entity.LoginEntity
import com.example.domain.param.LoginParam
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteAuthDataSource: RemoteAuthDataSource
) : AuthRepository {
    override suspend fun login(loginParam: LoginParam): LoginEntity =
        remoteAuthDataSource.login(loginParam)
}