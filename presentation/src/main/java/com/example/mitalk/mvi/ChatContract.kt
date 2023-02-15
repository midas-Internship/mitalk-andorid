package com.example.mitalk.mvi

data class ChatState(
    val accessToken: String = "",
    val remainPeople: String = ""
)

sealed class ChatSideEffect {
    object GetAccessSuccess : ChatSideEffect()
}