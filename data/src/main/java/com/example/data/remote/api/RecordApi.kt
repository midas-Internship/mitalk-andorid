package com.example.data.remote.api

import com.example.data.remote.response.RecordDetailResponse
import com.example.data.remote.response.RecordResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RecordApi {
    @GET("/customer/record")
    suspend fun getRecordList(): List<RecordResponse>

    @GET("/customer/{recordId}")
    suspend fun getRecordDetail(
        @Path("recordId") recordId: String
    ): RecordDetailResponse
}