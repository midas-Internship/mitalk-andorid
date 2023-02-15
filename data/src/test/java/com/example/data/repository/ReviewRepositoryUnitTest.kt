package com.example.data.repository

import com.example.data.remote.datasource.RemoteReviewDataSource
import com.example.domain.param.ReviewParam
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ReviewRepositoryUnitTest {
    private val remoteReviewDataSource = mock<RemoteReviewDataSource>()
    private val reviewRepository = ReviewRepositoryImpl(
        remoteReviewDataSource
    )

    @Test
    fun testPostReview() {
        val param = mock<ReviewParam>()
        runBlocking {
            whenever(remoteReviewDataSource.postReview(param)).thenReturn(Unit)
            val result = reviewRepository.postReview(param)
            assertEquals(Unit, result)
        }
    }
}