package com.example.mitalk.vm.question

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.QuestionUseCase
import com.example.mitalk.mvi.QuestionSideEffect
import com.example.mitalk.mvi.QuestionState
import com.example.mitalk.mvi.toState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionUseCase: QuestionUseCase,
) : ContainerHost<QuestionState, QuestionSideEffect>, ViewModel() {

    override val container = container<QuestionState, QuestionSideEffect>(QuestionState())

    fun getQuestionList() = intent {
        viewModelScope.launch {
            questionUseCase()
                .onSuccess {
                    reduce { state.copy(questionList = it.toState().questionList) }
                }.onFailure {
                    when (it) {
                        else -> QuestionSideEffect.UnknownException
                    }
                }
        }

    }

}