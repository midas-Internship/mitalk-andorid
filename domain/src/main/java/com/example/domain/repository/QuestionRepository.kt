package com.example.domain.repository

import com.example.domain.entity.QuestionEntity

interface QuestionRepository {

    suspend fun getQuestionList(): List<QuestionEntity>
}