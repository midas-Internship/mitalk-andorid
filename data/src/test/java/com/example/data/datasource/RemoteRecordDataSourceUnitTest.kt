package com.example.data.datasource

import com.example.data.remote.api.RecordApi
import com.example.data.remote.datasource.RemoteRecordDataSource
import com.example.data.remote.datasource.RemoteRecordDataSourceImpl
import com.example.data.remote.response.RecordResponse
import com.example.data.remote.response.toEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteRecordDataSourceUnitTest {
    private val recordApi = mock<RecordApi>()
    private val remoteRecordDataSource: RemoteRecordDataSource =
        RemoteRecordDataSourceImpl(recordApi)

    @Test
    fun testGetRecordList() {
        val response = listOf<RecordResponse>()
        runBlocking {
            whenever(recordApi.getRecordList()).thenReturn(response)
            val result = remoteRecordDataSource.getRecordList()
            assertEquals(response.map { it.toEntity() }, result)
        }
    }
}