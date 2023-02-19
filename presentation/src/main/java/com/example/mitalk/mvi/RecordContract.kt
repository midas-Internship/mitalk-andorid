package com.example.mitalk.mvi

import com.example.domain.entity.RecordEntity

data class RecordState(
    val recordList: List<RecordData> = listOf(),
) {
    data class RecordData(
        val recordId: String = "",
        val type: String = "",
        val counsellorName: String = "",
        val customerName: String = "",
        val time: String = "",
    )
}

fun RecordEntity.toStateData() = RecordState.RecordData(
    recordId = recordId,
    type = type,
    counsellorName = counsellorName,
    customerName = customerName,
    time = time,
)

sealed class RecordSideEffect {

}