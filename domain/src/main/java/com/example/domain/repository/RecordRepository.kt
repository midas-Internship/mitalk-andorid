package com.example.domain.repository

import com.example.domain.entity.RecordDetailEntity
import com.example.domain.entity.RecordEntity

interface RecordRepository {
    suspend fun getRecordList(): List<RecordEntity>
    suspend fun getRecordDetail(recordId: String): RecordDetailEntity
}