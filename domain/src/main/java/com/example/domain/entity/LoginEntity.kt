package com.example.domain.entity

data class LoginEntity(
    val accessToken: String,
    val refreshToken: String,
    val accessExp: String,
    val refreshExp: String
)