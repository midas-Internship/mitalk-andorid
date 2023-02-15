package com.example.data.remote.api

import com.example.data.remote.request.LoginRequest
import com.example.data.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("/customer/signin")
    suspend fun login(
        @Body login: LoginRequest
    ): LoginResponse

    @PATCH("/auth")
    suspend fun tokenRefresh(
        @Header("Refresh-Token") refreshToken: String
    ): LoginResponse
}