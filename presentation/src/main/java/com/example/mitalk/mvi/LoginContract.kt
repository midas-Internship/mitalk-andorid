package com.example.mitalk.mvi

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class LoginState(
    val result: GoogleSignInAccount? = null
)

sealed class LoginSideEffect {
    object LoginSuccess : LoginSideEffect()
}