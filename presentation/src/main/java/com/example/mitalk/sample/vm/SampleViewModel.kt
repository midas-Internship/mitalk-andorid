package com.example.mitalk.sample.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mitalk.sample.mvi.SampleEffect
import com.example.mitalk.sample.mvi.SampleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class SampleViewModel @Inject constructor(
) : ContainerHost<SampleState, SampleEffect>, ViewModel() {

    override val container =  container<SampleState, SampleEffect>(SampleState())

    fun checkCount(clickCount: Int) = intent {
        viewModelScope.launch {

        }
    }
}