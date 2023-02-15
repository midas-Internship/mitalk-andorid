package com.example.domain.usecase.review

import com.example.domain.repository.ReviewRepository
import javax.inject.Inject

class CheckReviewStateUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        reviewRepository.checkReviewState()
    }
}