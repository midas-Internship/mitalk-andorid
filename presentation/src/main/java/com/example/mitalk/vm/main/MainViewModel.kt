package com.example.mitalk.vm.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.param.ReviewParam
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.review.CheckReviewStateUseCase
import com.example.domain.usecase.review.PostReviewUseCase
import com.example.mitalk.mvi.MainSideEffect
import com.example.mitalk.mvi.MainState
import com.example.mitalk.mvi.ReviewSideEffect
import com.example.mitalk.ui.util.EvaluateItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val checkReviewStateUseCase: CheckReviewStateUseCase,
    private val postReviewUseCase: PostReviewUseCase,
) : ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container = container<MainState, MainSideEffect>(MainState())

    fun logout() = intent {
        viewModelScope.launch {
            logoutUseCase()
                .onSuccess {
                    postSideEffect(MainSideEffect.Logout)
                }
        }
    }

    fun checkReviewState() = intent {
        viewModelScope.launch {
            checkReviewStateUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            counsellorId = it.counsellorId,
                            counsellorName = it.name
                        )
                    }
                }
                .onFailure {
                }
        }
    }

    fun clearCounsellorId() = intent {
        reduce { state.copy(counsellorId = null) }
    }

    fun postReview(reviewParam: ReviewParam) = intent {
        viewModelScope.launch {
            postReviewUseCase(reviewParam = reviewParam)
                .onSuccess {
                    postSideEffect(MainSideEffect.ReviewSuccess)
                }.onFailure {

                }
        }
    }

    fun inputStarCount(starCount: Int) = intent {
        reduce { state.copy(starCount = starCount) }
    }

    fun inputEvaluateComment(evaluateComment: String) = intent {
        reduce { state.copy(evaluateComment = evaluateComment) }
    }

    fun inputGoodEvaluationSelected1(evaluation: EvaluateItemType?) = intent {
        reduce { state.copy(goodEvaluationSelected1 = evaluation) }
    }

    fun inputGoodEvaluationSelected2(evaluation: EvaluateItemType?) = intent {
        reduce { state.copy(goodEvaluationSelected2 = evaluation) }
    }

    fun inputBadEvaluationSelected1(evaluation: EvaluateItemType?) = intent {
        reduce { state.copy(badEvaluationSelected1 = evaluation) }
    }

    fun inputBadEvaluationSelected2(evaluation: EvaluateItemType?) = intent {
        reduce { state.copy(badEvaluationSelected2 = evaluation) }
    }
}
