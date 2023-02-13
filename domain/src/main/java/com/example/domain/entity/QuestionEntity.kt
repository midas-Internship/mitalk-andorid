package com.example.domain.entity

data class QuestionEntity(
    val questionList: List<QuestionData>,
) {
    data class QuestionData(
        val answer: String,
        val question: String,
    )
}
