package com.example.mitalk.mvi

import java.util.UUID

data class MainState(
    val counsellorId: UUID? = null,
)

sealed class MainSideEffect {
    object Logout : MainSideEffect()
}