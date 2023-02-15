package com.example.mitalk.mvi

data class MainState(
    val evaluateText: String = ""
)

sealed class MainSideEffect {
    object Logout : MainSideEffect()
}