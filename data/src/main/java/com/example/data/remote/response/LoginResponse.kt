package com.example.data.remote.response

import com.example.domain.entity.LoginEntity
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("accessExp")
    val accessExp: String,
    @SerializedName("refreshExp")
    val refreshExp: String,
)

fun LoginResponse.toEntity() = LoginEntity(
    accessToken = accessToken,
    refreshToken = refreshToken,
    accessExp = accessExp,
    refreshExp = refreshExp
)
