package com.example.mitalk.vm.question

import androidx.lifecycle.ViewModel
import com.example.mitalk.mvi.QuestionSideEffect
import com.example.mitalk.mvi.QuestionState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    
) : ContainerHost<QuestionState, QuestionSideEffect>, ViewModel() {

    override val container = container<QuestionState, QuestionSideEffect>(QuestionState())

    fun getQuestionList(){

    }

}