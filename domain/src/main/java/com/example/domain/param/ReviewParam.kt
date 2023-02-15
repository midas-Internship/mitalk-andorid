package com.example.domain.param

data class ReviewParam(
    val star: Int,
    val message: String?,
    val reviewItem: List<String>
)