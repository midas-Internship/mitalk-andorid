package com.example.data.remote.response

import com.example.domain.entity.RecordDetailEntity
import com.google.gson.annotations.SerializedName

data class RecordDetailResponse(
    @SerializedName("start_at")
    val startAt: String,
    @SerializedName("customer_name")
    val customerName: String,
    @SerializedName("counsellor_name")
    val counsellorName: String,
    @SerializedName("message_records")
    val messageRecords: List<MessageRecord>,
) {
    data class MessageRecord(
        @SerializedName("id")
        val messageId: String,
        @SerializedName("sender")
        val sender: String,
        @SerializedName("is_file")
        val isFile: Boolean,
        @SerializedName("is_deleted")
        val isDeleted: Boolean,
        @SerializedName("is_updated")
        val isUpdated: Boolean,
        @SerializedName("data_map")
        val dataMap: List<MessageData>,
    ) {
        data class MessageData(
            @SerializedName("message")
            val message: String,
            @SerializedName("local_date_time")
            val time: String
        )
    }
}

fun RecordDetailResponse.toEntity() = RecordDetailEntity(
    startAt = startAt,
    customerName = customerName,
    counsellorName = counsellorName,
    messageRecords = messageRecords.map { it.toEntity() }
)

fun RecordDetailResponse.MessageRecord.toEntity() = RecordDetailEntity.MessageRecord(
    messageId = messageId,
    sender = sender,
    isFile = isFile,
    isDeleted = isDeleted,
    isUpdated = isUpdated,
    dataMap = dataMap.map { it.toEntity() }
)

fun RecordDetailResponse.MessageRecord.MessageData.toEntity() =
    RecordDetailEntity.MessageRecord.MessageData(
        message = message,
        time = time
    )