package com.example.data.remote.datasource

import com.example.domain.entity.CheckReviewStateEntity
import com.example.domain.param.ReviewParam
import java.util.*

interface RemoteReviewDataSource {
    suspend fun postReview(reviewParam: ReviewParam)

    suspend fun checkReviewState(): CheckReviewStateEntity
}