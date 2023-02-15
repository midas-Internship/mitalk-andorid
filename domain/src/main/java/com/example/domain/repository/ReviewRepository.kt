package com.example.domain.repository

import com.example.domain.param.ReviewParam

interface ReviewRepository {
    suspend fun postReview(reviewParam: ReviewParam)
}