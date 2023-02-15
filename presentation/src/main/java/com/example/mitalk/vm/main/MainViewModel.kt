package com.example.mitalk.vm.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.LogoutUseCase
import com.example.domain.usecase.review.CheckReviewStateUseCase
import com.example.mitalk.mvi.MainSideEffect
import com.example.mitalk.mvi.MainState
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
                .onSuccess { reduce { state.copy(counsellorId = it) } }
        }
    }

    fun clearCounsellorId() = intent {
        reduce { state.copy(counsellorId = null) }
    }
}
