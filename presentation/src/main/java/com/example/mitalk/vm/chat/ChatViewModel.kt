package com.example.mitalk.vm.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.ChatInfoEntity
import com.example.domain.usecase.auth.GetAccessTokenUseCase
import com.example.domain.usecase.chat.ClearChatInfoUseCase
import com.example.domain.usecase.chat.FetchChatInfoUseCase
import com.example.domain.usecase.chat.SaveChatInfoUseCase
import com.example.mitalk.mvi.ChatSideEffect
import com.example.mitalk.mvi.ChatState
import com.example.mitalk.socket.ChatTypeSocket
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
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val saveChatInfoUseCase: SaveChatInfoUseCase,
    private val fetchChatInfoUseCase: FetchChatInfoUseCase,
    private val clearChatInfoUseCase: ClearChatInfoUseCase
) : ContainerHost<ChatState, ChatSideEffect>, ViewModel() {
    override val container = container<ChatState, ChatSideEffect>(ChatState())

    fun getAccessToken() = intent {
        viewModelScope.launch {
            getAccessTokenUseCase()
                .onSuccess {
                    reduce { state.copy(accessToken = it) }
                }
        }
    }

    fun saveChatInfo(chatInfoEntity: ChatInfoEntity) = intent {
        viewModelScope.launch {
            saveChatInfoUseCase(chatInfoEntity = chatInfoEntity)
        }
    }

    fun fetchChatInfo() = intent {
        viewModelScope.launch {
            fetchChatInfoUseCase()
                .onSuccess {
                    postSideEffect(ChatSideEffect.ChatInfo(it))
                }
        }
    }

    fun clearChatInfo() = intent {
        viewModelScope.launch {
            clearChatInfoUseCase()
        }
    }

    fun setRemainPeople(remainPeople: String) = intent {
        reduce { state.copy(remainPeople = remainPeople) }
    }

    fun successRoom(roomId: String) = intent {
        postSideEffect(ChatSideEffect.SuccessRoom(roomId))
    }

    fun setChatTypeSocket(
        chatTypeSocket: ChatTypeSocket
    ) = intent {
        reduce { state.copy(chatTypeSocket = chatTypeSocket) }
    }
}