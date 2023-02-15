package com.example.data.repository

import com.example.data.remote.datasource.RemoteReviewDataSource
import com.example.domain.repository.ReviewRepository
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val remoteReviewDataSource: RemoteReviewDataSource
) : ReviewRepository {
}