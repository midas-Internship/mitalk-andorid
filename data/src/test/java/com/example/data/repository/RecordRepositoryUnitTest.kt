package com.example.data.repository

import com.example.data.remote.datasource.RemoteRecordDataSource
import com.example.domain.entity.RecordDetailEntity
import com.example.domain.entity.RecordEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RecordRepositoryUnitTest {
    private val remoteRecordDataSource = mock<RemoteRecordDataSource>()
    private val recordRepository = RecordRepositoryImpl(
        remoteRecordDataSource
    )

    @Test
    fun testGetRecordList() {
        val entity = mock<List<RecordEntity>>()
        runBlocking {
            whenever(remoteRecordDataSource.getRecordList()).thenReturn(entity)
            val result = recordRepository.getRecordList()
            assertEquals(entity, result)
        }
    }

    @Test
    fun testGetRecordDetail() {
        val recordId = "1"
        val entity = mock<RecordDetailEntity>()
        runBlocking {
            whenever(remoteRecordDataSource.getRecordDetail(recordId = recordId)).thenReturn(entity)
            val result = recordRepository.getRecordDetail(recordId = recordId)
            assertEquals(entity, result)
        }
    }
}