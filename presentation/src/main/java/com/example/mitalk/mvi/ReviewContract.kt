package com.example.mitalk.mvi

sealed class ReviewSideEffect {
    object ReviewSuccess : ReviewSideEffect()
}
