package com.example.usecase.record

import com.example.domain.entity.RecordEntity
import com.example.domain.repository.RecordRepository
import com.example.domain.usecase.record.GetRecordListUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRecordListUnitTest {
    private val recordRepository = mock<RecordRepository>()
    private val getRecordListUseCase = GetRecordListUseCase(recordRepository)

    @Test
    fun testGetRecordList() {
        val entity = mock<List<RecordEntity>>()
        runBlocking {
            whenever(recordRepository.getRecordList()).thenReturn(entity)
            getRecordListUseCase().onSuccess {result ->
                assertEquals(result, entity)
            }
        }
    }
}