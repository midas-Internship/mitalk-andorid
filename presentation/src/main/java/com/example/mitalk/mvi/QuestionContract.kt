package com.example.mitalk.mvi

import com.example.domain.entity.QuestionEntity

data class QuestionState(
    val questionList: List<QuestionEntity> = listOf(),
)
sealed class QuestionSideEffect {

    object UnknownException : QuestionSideEffect()
}