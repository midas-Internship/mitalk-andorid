package com.example.mitalk.mvi

import com.example.domain.entity.QuestionEntity

data class QuestionState(
    val questionList: List<QuestionData> = listOf(),
) {
    data class QuestionData(
        val answer: String,
        val question: String,
    )
}

fun QuestionEntity.toState() = QuestionState(
        questionList = questionList.map { it.toStateData() }
    )

fun QuestionEntity.QuestionData.toStateData() =
    QuestionState.QuestionData(
        answer = answer,
        question = question,
    )

sealed class QuestionSideEffect {

    object UnknownException : QuestionSideEffect()
}