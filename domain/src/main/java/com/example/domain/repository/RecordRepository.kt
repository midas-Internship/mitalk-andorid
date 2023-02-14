package com.example.domain.repository

import com.example.domain.entity.RecordEntity

interface RecordRepository {
    suspend fun getRecordList(): List<RecordEntity>
}