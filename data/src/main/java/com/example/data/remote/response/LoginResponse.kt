package com.example.data.remote.response

import com.example.domain.entity.LoginEntity
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("access_exp")
    val accessExp: String,
    @SerializedName("refresh_exp")
    val refreshExp: String,
)

fun LoginResponse.toEntity() = LoginEntity(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessExp = accessExp,
    refreshExp = refreshExp
)
