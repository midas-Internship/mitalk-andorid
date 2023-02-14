package com.example.data.remote.datasource

import com.example.domain.entity.QuestionEntity

interface RemoteQuestionDataSource {
    suspend fun getQuestionList(): QuestionEntity
}