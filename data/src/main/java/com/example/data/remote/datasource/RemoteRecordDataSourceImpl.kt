package com.example.data.remote.datasource

import com.example.data.remote.api.RecordApi
import com.example.data.remote.response.toEntity
import com.example.data.remote.util.miTalkApiCall
import com.example.domain.entity.RecordEntity
import javax.inject.Inject

class RemoteRecordDataSourceImpl @Inject constructor(
    private val recordApi: RecordApi
) : RemoteRecordDataSource {
    override suspend fun getRecordList(): List<RecordEntity> = miTalkApiCall {
        recordApi.getRecordList().map { it.toEntity() }
    }
}