package com.example.mitalk.mvi

import com.example.domain.entity.RecordDetailEntity

data class RecordDetailState(
    val startAt: String = "",
    val customerName: String = "",
    val counsellorName: String = "",
    val messageRecords: List<MessageRecordData> = listOf(),
) {
    data class MessageRecordData(
        val sender: String,
        val isFile: Boolean,
        val isDeleted: Boolean,
        val isUpdated: Boolean,
        val dataMap: List<Map<String, String>>,
    )
}

fun RecordDetailEntity.MessageRecord.toSateData() = RecordDetailState.MessageRecordData(
    sender = sender,
    isFile = isFile,
    isDeleted =  isDeleted,
    isUpdated = isUpdated,
    dataMap = dataMap
)

sealed class RecordDetailSideEffect {

}