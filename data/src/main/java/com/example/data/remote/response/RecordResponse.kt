package com.example.data.remote.response

import com.example.domain.entity.RecordEntity
import com.google.gson.annotations.SerializedName

data class RecordResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("counsellor_name")
    val counsellorName: String,
    @SerializedName("customer_name")
    val customerName: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("record_id")
    val recordId: String
)

fun RecordResponse.toEntity() = RecordEntity(
    type = type,
    counsellorName = counsellorName,
    customerName = customerName,
    time = time,
    recordId = recordId
)