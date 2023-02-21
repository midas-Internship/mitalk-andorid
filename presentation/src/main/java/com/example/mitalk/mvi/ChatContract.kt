package com.example.mitalk.mvi

import android.net.Uri
import com.example.mitalk.socket.ChatSocket
import com.example.mitalk.ui.chat.ChatData


data class ChatState(
    val accessToken: String = "",
    val remainPeople: String = "",
    val chatSocket: ChatSocket = ChatSocket({}, {}, {}, {}, {}, {}, {}, {}),
    val chatList: List<ChatData> = mutableListOf(),
    val uploadList: List<Uri> = mutableListOf(),
    val callCheck: Boolean = false,
    val chatType: String = "",
)

sealed class ChatSideEffect {
    data class ReceiveChat(val chat: ChatData, val chatSize: Int) : ChatSideEffect()
    data class ReceiveChatUpdate(val chat: ChatData) : ChatSideEffect()
    data class ReceiveChatDelete(val chatId: String) : ChatSideEffect()
    data class SuccessRoom(val roomId: String) : ChatSideEffect()
    data class SuccessUpload(val url: String) : ChatSideEffect()
    object FinishRoom : ChatSideEffect()
    data class FileSizeException(val uri: Uri) : ChatSideEffect()
    object FileOverException : ChatSideEffect()
    object FileNotAllowedException : ChatSideEffect()
}