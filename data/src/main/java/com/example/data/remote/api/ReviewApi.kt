package com.example.data.remote.api

import com.example.data.remote.request.ReviewRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ReviewApi {
    @POST("/customer/review")
    suspend fun postReview(
        @Body reviewRequest: ReviewRequest
    )
}