package com.example.domain.param

import java.util.*

data class ReviewParam(
    val star: Int?,
    val message: String?,
    val reviewItem: List<String>,
    val counsellorId: UUID?,
)