package com.example.data.remote.response

import android.util.Log
import com.example.domain.entity.LoginEntity
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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

fun LoginResponse.toEntity(): LoginEntity =
    LoginEntity(
        accessToken = accessToken,
        refreshToken = refreshToken,
        accessExp = ZonedDateTime.parse(accessExp),
        refreshExp = ZonedDateTime.parse(accessExp),
    )
