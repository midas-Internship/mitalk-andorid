package com.example.domain.repository

import com.example.domain.entity.LoginEntity
import com.example.domain.param.LoginParam

interface AuthRepository {
    suspend fun login(loginParam: LoginParam)

    suspend fun autoLogin()

    suspend fun logout()

    suspend fun tokenRefresh()
}