package com.example.data.remote.response

import com.example.domain.entity.RecordEntity
import com.google.gson.annotations.SerializedName

data class RecordResponse(
    @SerializedName("records")
    val records: List<Record>,
) {
    data class Record(
        @SerializedName("record_id")
        val recordId: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("counsellor_name")
        val counsellorName: String,
        @SerializedName("customer_name")
        val customerName: String,
        @SerializedName("start_at")
        val startTime: String,
    )
}

fun RecordResponse.Record.toEntity() = RecordEntity(
    recordId = recordId,
    type = type,
    counsellorName = counsellorName,
    customerName = customerName,
    time = startTime
)
