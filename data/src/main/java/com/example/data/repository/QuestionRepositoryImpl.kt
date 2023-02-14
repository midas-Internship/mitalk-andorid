package com.example.data.repository

import com.example.data.remote.datasource.RemoteQuestionDataSource
import com.example.domain.entity.QuestionEntity
import com.example.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val remoteQuestionDataSource: RemoteQuestionDataSource,
): QuestionRepository {
    override suspend fun getQuestionList(): QuestionEntity =
        remoteQuestionDataSource.getQuestionList()

}