package com.example.data.remote.datasource

import com.example.data.remote.api.ReviewApi
import javax.inject.Inject

class RemoteReviewDataSourceImpl @Inject constructor(
    private val reviewApi: ReviewApi
) : RemoteReviewDataSource {
}