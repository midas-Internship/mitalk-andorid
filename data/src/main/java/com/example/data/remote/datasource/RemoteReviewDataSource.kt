package com.example.data.remote.datasource

import com.example.domain.param.ReviewParam

interface RemoteReviewDataSource {
    suspend fun postReview(reviewParam: ReviewParam)
}