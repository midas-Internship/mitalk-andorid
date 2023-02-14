package com.example.data.remote.datasource

import com.example.data.remote.api.AuthApi
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.toRequest
import com.example.data.remote.response.toEntity
import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.LoginEntity
import com.example.domain.param.LoginParam
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : RemoteAuthDataSource {
    override suspend fun login(loginParam: LoginParam): LoginEntity = miTalkApiCall {
        authApi.login(loginParam.toRequest()).toEntity()
    }
}