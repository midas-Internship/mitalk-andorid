package com.example.data.remote.api

import com.example.data.remote.response.QuestionResponse
import retrofit2.http.GET

interface QuestionApi {

    @GET("question")
    fun getQuestionList(): QuestionResponse
}