package com.example.domain.entity

import java.time.ZonedDateTime

data class LoginEntity(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: ZonedDateTime,
    val refreshExp: ZonedDateTime
)