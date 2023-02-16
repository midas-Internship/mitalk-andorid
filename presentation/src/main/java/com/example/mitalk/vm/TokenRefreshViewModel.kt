package com.example.mitalk.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.TokenRefreshUseCase
import com.example.mitalk.util.MutableEventFlow
import com.example.mitalk.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenRefreshViewModel @Inject constructor(
    private val tokenRefreshUseCase: TokenRefreshUseCase,
): ViewModel() {

    private val _token = MutableEventFlow<Event>()
    val token = _token.asEventFlow()

    fun tokenRefresh() {
        viewModelScope.launch {
            tokenRefreshUseCase()
                .onSuccess { _token.emit(Event.Success) }
                .onFailure { _token.emit(Event.Fail) }
        }
    }

    sealed class Event {
        object Success : Event()
        object Fail : Event()
    }
}