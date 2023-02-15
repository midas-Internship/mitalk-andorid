package com.example.domain.entity

data class RecordDetailEntity(
    val startAt: String,
    val customerName: String,
    val counsellorName: String,
    val messageRecords: List<MessageRecord>,
) {
    data class MessageRecord(
        val sender: String,
        val isFile: Boolean,
        val isDeleted: Boolean,
        val isUpdated: Boolean,
        val dataMap: List<Map<String, String>>,
    )
}