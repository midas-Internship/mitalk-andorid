package com.example.mitalk.vm.review

import androidx.lifecycle.ViewModel
import com.example.domain.param.ReviewParam
import com.example.domain.usecase.review.PostReviewUseCase
import com.example.mitalk.mvi.ReviewSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val postReviewUseCase: PostReviewUseCase
) : ContainerHost<Unit, ReviewSideEffect>, ViewModel() {
    override val container = container<Unit, ReviewSideEffect>(Unit)

    fun postReview(reviewParam: ReviewParam) = intent {
        kotlin.runCatching {
            postReviewUseCase(reviewParam = reviewParam)
        }.onSuccess {
            postSideEffect(ReviewSideEffect.ReviewSuccess)
        }.onFailure {

        }
    }
}