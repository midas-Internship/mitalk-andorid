package com.example.data.remote.datasource

import com.example.domain.entity.RecordEntity

interface RemoteRecordDataSource {
    suspend fun getRecordList(): List<RecordEntity>
}