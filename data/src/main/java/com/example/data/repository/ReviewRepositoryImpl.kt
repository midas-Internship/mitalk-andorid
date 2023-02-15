package com.example.data.repository

import com.example.data.remote.datasource.RemoteReviewDataSource
import com.example.domain.entity.CheckReviewStateEntity
import com.example.domain.param.ReviewParam
import com.example.domain.repository.ReviewRepository
import java.util.*
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val remoteReviewDataSource: RemoteReviewDataSource
) : ReviewRepository {
    override suspend fun postReview(reviewParam: ReviewParam) =
        remoteReviewDataSource.postReview(reviewParam = reviewParam)

    override suspend fun checkReviewState(): CheckReviewStateEntity =
        remoteReviewDataSource.checkReviewState()
}