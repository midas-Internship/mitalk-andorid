package com.example.data.repository

import com.example.data.remote.datasource.RemoteRecordDataSource
import com.example.domain.entity.RecordDetailEntity
import com.example.domain.entity.RecordEntity
import com.example.domain.repository.RecordRepository
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(
    private val remoteRecordDataSource: RemoteRecordDataSource
) : RecordRepository {
    override suspend fun getRecordList(): List<RecordEntity> =
        remoteRecordDataSource.getRecordList()

    override suspend fun getRecordDetail(recordId: String): RecordDetailEntity =
        remoteRecordDataSource.getRecordDetail(recordId = recordId)
}