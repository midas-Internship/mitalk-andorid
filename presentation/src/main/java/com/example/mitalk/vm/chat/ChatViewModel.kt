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
import com.example.domain.usecase.record.GetRecordDetailUseCase
import com.example.mitalk.mvi.ChatSideEffect
import com.example.mitalk.mvi.ChatState
import com.example.mitalk.mvi.toChatData
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
    private val getRecordDetailUseCase: GetRecordDetailUseCase,
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

    fun loadChatData(roomId: String, deleteMsg: String) = intent {
        reduce { state.copy(callCheck = true) }
        viewModelScope.launch {
            getRecordDetailUseCase(recordId = roomId)
                .onSuccess {
                    reduce {
                        state.copy(chatList = it.messageRecords.map {
                            it.toChatData(
                                deleteMsg
                            )
                        })
                    }
                }
        }
    }

    fun clearChatData() = intent {
        reduce { state.copy(chatList = listOf(), uploadList = listOf(), callCheck = false) }
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
                    reduce { state.copy(chatType = it.chatType) }
                }
        }
    }

    fun clearChatInfo() = intent {
        viewModelScope.launch {
            clearChatInfoUseCase()
        }
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
            reduce { state.copy(uploadList = state.uploadList.plus(uri)) }
            viewModelScope.launch {
                postFileUseCase(file)
                    .onSuccess {
                        reduce { state.copy(uploadList = state.uploadList.filter { uploadUri -> uri != uploadUri }) }
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

    fun setChatTypeSocket() = intent {
        reduce {
            state.copy(chatSocket = ChatSocket(
                failAction = {
                    crowedService()
                }, waitingAction = {
                    setRemainPeople(it)
                }, successAction = {
                    successRoom(it)
                }, receiveAction = {
                    receiveChat(it)
                }, receiveActionUpdate = {
                    receiveChatUpdate(it)
                }, receiveActionDelete = {
                    receiveChatDelete(it)
                }, finishAction = {
                    finishRoom()
                }, errorAction = {
                    errorSocket()
                })
            )
        }
    }

    private fun crowedService() = intent {
        postSideEffect(ChatSideEffect.CrowedService)
    }

    private fun setRemainPeople(remainPeople: String) = intent {
        reduce { state.copy(remainPeople = remainPeople) }
        postSideEffect(ChatSideEffect.WaitingRoom)
    }

    private fun successRoom(name: String) = intent {
        reduce { state.copy(counsellorName = name, callCheck = true) }
        postSideEffect(ChatSideEffect.SuccessRoom(name))
    }

    private fun finishRoom() = intent {
        clearChatData()
        postSideEffect(ChatSideEffect.FinishRoom)
    }

    private fun errorSocket() = intent {
        postSideEffect(ChatSideEffect.ErrorSocket)
    }

    private fun receiveChat(chat: ChatData) = intent {
        reduce { state.copy(chatList = state.chatList.plus(chat)) }
        postSideEffect(ChatSideEffect.ReceiveChat(chat, state.chatList.size))
    }

    private fun receiveChatUpdate(chat: ChatData) = intent {
        postSideEffect(ChatSideEffect.ReceiveChatUpdate(chat))
    }

    private fun receiveChatDelete(chatId: String) = intent {
        postSideEffect(ChatSideEffect.ReceiveChatDelete(chatId))
    }
}