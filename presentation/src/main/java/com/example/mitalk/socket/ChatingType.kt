package com.example.mitalk.socket

import com.example.mitalk.util.toChatTime
import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class SocketType(
    @SerializedName("type")
    val type: String?,
)

data class FailWaitingRoom(
    @SerializedName("message")
    val message: String,
)

data class WaitingRoom(
    @SerializedName("order")
    val order: String,
    @SerializedName("message")
    val message: String,
)

data class SuccessRoom(
    @SerializedName("room_id")
    val roomId: String,
)

data class ChatData(
    @SerializedName("room_id")
    val roomId: String,
    @SerializedName("message_id")
    val messageId: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("chat_message_type")
    val chatMessageType: String,
    @SerializedName("message")
    val message: String?,
)

fun ChatData.toUseData() = com.example.mitalk.ui.chat.ChatData(
    id = messageId,
    text = message ?: "",
    isMe = role == "CUSTOMER",
    time = LocalTime.now()
)

fun com.example.mitalk.ui.chat.ChatData.toDeleteChatData(deleteMsg: String) =
    com.example.mitalk.ui.chat.ChatData(
        id = id,
        text = deleteMsg,
        isMe = isMe,
        time = time
    )