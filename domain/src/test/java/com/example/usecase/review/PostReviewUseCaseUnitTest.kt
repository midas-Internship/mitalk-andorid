package com.example.usecase.review

import com.example.domain.param.ReviewParam
import com.example.domain.repository.ReviewRepository
import com.example.domain.usecase.review.PostReviewUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PostReviewUseCaseUnitTest {
    private val reviewRepository = mock<ReviewRepository>()
    private val postReviewUseCase = PostReviewUseCase(reviewRepository)

    @Test
    fun testPostReview() {
        val param = mock<ReviewParam>()
        runBlocking {
            whenever(reviewRepository.postReview(param)).thenReturn(Unit)
            postReviewUseCase(param).onSuccess { result ->
                assertEquals(result, Unit)
            }
        }
    }
}