package com.example.data.remote.api

import com.example.data.remote.response.RecordResponse
import retrofit2.http.GET

interface RecordApi {
    @GET("/customer/record")
    suspend fun getRecordList(): List<RecordResponse>
}