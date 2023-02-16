package com.example.mitalk.mvi

import com.example.mitalk.socket.ChatTypeSocket


data class ChatState(
    val accessToken: String = "",
    val remainPeople: String = "",
    val chatTypeSocket: ChatTypeSocket = ChatTypeSocket()
)

sealed class ChatSideEffect {
    data class SuccessRoom(val roomId: String) : ChatSideEffect()
}