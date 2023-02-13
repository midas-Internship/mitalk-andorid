package com.example.mitalk.mvi

data class QuestionState(
    val body: String = "",
    val question: String = "",
    val answer: String = ""
)

sealed class QuestionSideEffect {
}