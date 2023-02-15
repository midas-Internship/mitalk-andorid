package com.example.mitalk.vm.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.auth.GetAccessTokenUseCase
import com.example.mitalk.mvi.ChatSideEffect
import com.example.mitalk.mvi.ChatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase
): ContainerHost<ChatState, ChatSideEffect>, ViewModel() {
    override val container = container<ChatState, ChatSideEffect>(ChatState())

    fun getAccessToken() = intent {
        viewModelScope.launch {
            getAccessTokenUseCase()
                .onSuccess {
                    reduce { state.copy(accessToken = it) }
                    postSideEffect(ChatSideEffect.GetAccessSuccess)
                }
        }
    }

    fun setRemainPeople(remainPeople: String) = intent {
        reduce { state.copy(remainPeople = remainPeople) }
    }
}