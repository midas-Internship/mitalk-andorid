package com.example.mitalk.mvi

import android.net.Uri
import com.example.domain.entity.ChatInfoEntity
import com.example.mitalk.socket.ChatSocket
import com.example.mitalk.ui.chat.ChatData


data class ChatState(
    val accessToken: String = "",
    val remainPeople: String = "",
    val chatSocket: ChatSocket = ChatSocket(),
    val chatList: List<ChatData> = mutableListOf()
)

sealed class ChatSideEffect {
    data class ReceiveChat(val chat: ChatData) : ChatSideEffect()
    data class ReceiveChatUpdate(val chat: ChatData) : ChatSideEffect()
    data class ReceiveChatDelete(val chatId: String) : ChatSideEffect()
    data class SuccessRoom(val roomId: String) : ChatSideEffect()
    data class SuccessUpload(val url: String) : ChatSideEffect()
    data class ChatInfo(val chatInfoEntity: ChatInfoEntity) : ChatSideEffect()
    object FinishRoom : ChatSideEffect()
    data class FileSizeException(val uri: Uri) : ChatSideEffect()
    object FileOverException : ChatSideEffect()
    object FileNotAllowedException : ChatSideEffect()
}