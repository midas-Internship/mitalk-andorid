package com.example.data.repository

import com.example.domain.entity.QuestionEntity
import com.example.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(

): QuestionRepository {
    override suspend fun getQuestionList(): QuestionEntity =
        QuestionEntity(questionList = listOf(QuestionEntity.QuestionData("","")))

}