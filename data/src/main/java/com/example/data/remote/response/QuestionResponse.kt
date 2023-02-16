package com.example.data.remote.response

import com.example.domain.entity.QuestionEntity
import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @SerializedName("answer") val answer: String,
    @SerializedName("question") val question: String,
)

fun QuestionResponse.toEntity() =
    QuestionEntity(
        answer = answer,
        question = question,
    )