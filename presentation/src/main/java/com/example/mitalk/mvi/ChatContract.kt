package com.example.mitalk.mvi

import com.example.domain.entity.ChatInfoEntity
import com.example.mitalk.socket.ChatTypeSocket


data class ChatState(
    val accessToken: String = "",
    val remainPeople: String = "",
    val chatTypeSocket: ChatTypeSocket = ChatTypeSocket()
)

sealed class ChatSideEffect {
    data class AccessToken(val accessToken: String) : ChatSideEffect()

    data class ReceiveChat(val chat: String): ChatSideEffect()
    data class SuccessRoom(val roomId: String) : ChatSideEffect()
    data class ChatInfo(val chatInfoEntity: ChatInfoEntity) : ChatSideEffect()
}