package com.example.data.remote.datasource

import com.example.domain.entity.LoginEntity
import com.example.domain.param.LoginParam

interface RemoteAuthDataSource {
    suspend fun login(loginParam: LoginParam): LoginEntity
}