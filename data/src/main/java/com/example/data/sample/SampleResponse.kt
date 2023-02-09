package com.example.data.sample

import com.example.domain.sample.SampleEntity

data class SampleResponse(
    val returnCount: String,
)

fun SampleResponse.toEntity() = SampleEntity(
    returnCount = returnCount,
)
