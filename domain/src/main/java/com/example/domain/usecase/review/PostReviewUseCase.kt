package com.example.domain.usecase.review

import com.example.domain.param.ReviewParam
import com.example.domain.repository.ReviewRepository
import javax.inject.Inject

class PostReviewUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    suspend operator fun invoke(reviewParam: ReviewParam) = kotlin.runCatching {
        reviewRepository.postReview(reviewParam = reviewParam)
    }
}