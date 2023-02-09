package com.example.mitalk.ui.sample.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.exception.BadRequestException
import com.example.domain.exception.TooManyRequestsException
import com.example.domain.sample.SampleUseCase
import com.example.mitalk.ui.sample.ui.mvi.SampleEffect
import com.example.mitalk.ui.sample.ui.mvi.SampleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val sampleUseCase: SampleUseCase,
) :  ContainerHost<SampleState, SampleEffect>, ViewModel() {

    override val container =  container<SampleState, SampleEffect>(SampleState())

    fun checkCount(clickCount: Int) = intent {
        viewModelScope.launch {
            sampleUseCase(
                clickCount = clickCount,
            ).onSuccess {
                reduce { state.copy(returnCount = it.returnCount) }
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(SampleEffect.CannotCountClickCount)
                    is TooManyRequestsException -> postSideEffect(SampleEffect.TooManyClickCount)
                    else -> postSideEffect(SampleEffect.UnknownException)
                }
            }
        }
    }

    fun inputClickCount(data: Int) = intent {
        reduce { state.copy(clickCount = data + 1) }
    }
}