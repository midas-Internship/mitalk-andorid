package com.example.mitalk.vm.chat

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.ChatInfoEntity
import com.example.domain.usecase.auth.GetAccessTokenUseCase
import com.example.domain.usecase.chat.ClearChatInfoUseCase
import com.example.domain.usecase.chat.FetchChatInfoUseCase
import com.example.domain.usecase.chat.SaveChatInfoUseCase
import com.example.domain.usecase.file.PostFileUseCase
import com.example.mitalk.mvi.ChatSideEffect
import com.example.mitalk.mvi.ChatState
import com.example.mitalk.socket.ChatSocket
import com.example.mitalk.socket.toDeleteChatData
import com.example.mitalk.ui.chat.ChatData
import com.example.mitalk.util.FileNotAllowedException
import com.example.mitalk.util.FileOverException
import com.example.mitalk.util.FileSizeException
import com.example.mitalk.util.toFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val saveChatInfoUseCase: SaveChatInfoUseCase,
    private val fetchChatInfoUseCase: FetchChatInfoUseCase,
    private val clearChatInfoUseCase: ClearChatInfoUseCase,
    private val postFileUseCase: PostFileUseCase,
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

    fun addChatList(chatData: ChatData) = intent {
        reduce { state.copy(chatList = state.chatList.plus(chatData)) }
    }

    fun editChatList(chatData: ChatData) = intent {
        reduce {
            state.copy(chatList = state.chatList.map {
                if (it.id == chatData.id) chatData else it
            })
        }
    }

    fun deleteChatList(chatId: String, deleteMsg: String) = intent {
        reduce {
            state.copy(chatList = state.chatList.map {
                if (it.id == chatId) it.toDeleteChatData(
                    deleteMsg
                ) else it
            })
        }
    }

    fun postFile(uri: Uri, context: Context, approve: Boolean = false) = intent {
        kotlin.runCatching {
            uri.toFile(context, approve)
        }.onSuccess { file ->
            viewModelScope.launch {
                postFileUseCase(file)
                    .onSuccess {
                        postSideEffect(ChatSideEffect.SuccessUpload(it.file))
                    }.onFailure {
                    }
            }
        }.onFailure {
            when (it) {
                is FileSizeException -> {
                    postSideEffect(ChatSideEffect.FileSizeException(uri = uri))
                }
                is FileOverException -> {
                    postSideEffect(ChatSideEffect.FileOverException)
                }
                is FileNotAllowedException -> {
                    postSideEffect(ChatSideEffect.FileNotAllowedException)
                }
            }
        }
    }

    fun setRemainPeople(remainPeople: String) = intent {
        reduce { state.copy(remainPeople = remainPeople) }
    }

    fun successRoom(roomId: String) = intent {
        postSideEffect(ChatSideEffect.SuccessRoom(roomId))
    }

    fun finishRoom() = intent {
        postSideEffect(ChatSideEffect.FinishRoom)
    }

    fun receiveChat(chat: ChatData) = intent {
        postSideEffect(ChatSideEffect.ReceiveChat(chat))
    }

    fun receiveChatUpdate(chat: ChatData) = intent {
        postSideEffect(ChatSideEffect.ReceiveChatUpdate(chat))
    }

    fun receiveChatDelete(chatId: String) = intent {
        postSideEffect(ChatSideEffect.ReceiveChatDelete(chatId))
    }

    fun setChatTypeSocket(
        chatSocket: ChatSocket,
    ) = intent {
        reduce { state.copy(chatSocket = chatSocket) }
    }
}