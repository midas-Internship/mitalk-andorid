package com.example.mitalk.mvi

data class LoginState(
    val accessToken: String = "",
    val refreshToken: String = "",
    val accessExp: String = "",
    val refreshExp: String = "",
)

sealed class LoginSideEffect {
    object LoginSuccess : LoginSideEffect()
}