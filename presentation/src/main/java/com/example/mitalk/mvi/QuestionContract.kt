package com.example.mitalk.mvi
data class QuestionState(
    val questionList: List<QuestionData> = listOf(),
    val answer: String = "",
) {
    data class QuestionData(
        val body: String,
        val question: String,
    )
}

sealed class QuestionSideEffect {

    object UnknownException : QuestionSideEffect()
}