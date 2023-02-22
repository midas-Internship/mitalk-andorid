package com.example.data.remote.datasource

import com.example.data.remote.api.QuestionApi
import com.example.data.remote.response.toEntity
import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.QuestionEntity
import javax.inject.Inject

class RemoteQuestionDataSourceImpl @Inject constructor(
    private val questionApi: QuestionApi,
): RemoteQuestionDataSource {
    override suspend fun getQuestionList(): List<QuestionEntity> = miTalkApiCall {
        questionApi.getQuestionList().map {  it.toEntity() }
    }
}