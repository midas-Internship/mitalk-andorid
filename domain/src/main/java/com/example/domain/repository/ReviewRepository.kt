package com.example.domain.repository

import com.example.domain.param.ReviewParam
import java.util.UUID

interface ReviewRepository {
    suspend fun postReview(reviewParam: ReviewParam)
    suspend fun checkReviewState(): UUID?
}