package com.example.data.remote.api

import com.example.data.remote.request.ReviewRequest
import com.example.data.remote.response.CheckReviewStateResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ReviewApi {
    @POST("/customer/review")
    suspend fun postReview(
        @Body reviewRequest: ReviewRequest
    )

    @GET("/customer/review")
    suspend fun checkReviewState(): CheckReviewStateResponse
}