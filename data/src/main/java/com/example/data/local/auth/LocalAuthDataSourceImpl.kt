package com.example.data.local.auth

import android.util.Log
import com.example.data.local.AuthPreference
import com.example.domain.entity.LoginEntity
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val authPreference: AuthPreference,
): LocalAuthDataSource {
    override suspend fun fetchToken(): LoginEntity =
        with(authPreference) {
            LoginEntity(
                accessToken = fetchAccessToken(),
                refreshToken = fetchRefreshToken(),
                accessExp = fetchRefreshExp(),
                refreshExp = fetchRefreshExp(),
            )
        }


    override suspend fun saveToken(loginEntity: LoginEntity) =
        with(authPreference) {
            saveAccessToken(loginEntity.accessToken)
            saveRefreshToken(loginEntity.refreshToken)
            saveRefreshExp(loginEntity.refreshExp)
        }


    override suspend fun clearToken() =
        with(authPreference) {
            clearAccessToken()
            clearRefreshToken()
            clearRefreshExp()
        }
}