package com.example.usecase.record

import com.example.domain.entity.RecordDetailEntity
import com.example.domain.entity.RecordEntity
import com.example.domain.repository.RecordRepository
import com.example.domain.usecase.record.GetRecordDetailUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetRecordDetailUseCaseUniTest {
    private val recordRepository = mock<RecordRepository>()
    private val getRecordDetailUseCase = GetRecordDetailUseCase(recordRepository)

    @Test
    fun testGetRecordList() {
        val recordId = "1"
        val entity = mock<RecordDetailEntity>()
        runBlocking {
            whenever(recordRepository.getRecordDetail(recordId)).thenReturn(entity)
            getRecordDetailUseCase(recordId).onSuccess { result ->
                Assert.assertEquals(result, entity)
            }
        }
    }
}