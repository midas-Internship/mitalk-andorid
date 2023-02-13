package com.example.data.remote.response

import com.example.domain.entity.QuestionEntity
import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @SerializedName("??") val questionList: List<QuestionData>,
) {
    data class QuestionData(
        @SerializedName("answer") val answer: String,
        @SerializedName("question") val question: String,
    )
}

fun QuestionResponse.toEntity() =
    QuestionEntity(
        questionList = questionList.map { it.toData() }
    )

fun QuestionResponse.QuestionData.toData() =
    QuestionEntity.QuestionData(
        answer = answer,
        question = question,
    )
