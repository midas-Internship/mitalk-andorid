package com.example.mitalk.socket

import com.example.mitalk.BuildConfig
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject

class ChatSocket(
    failAction: () -> Unit,
    waitingAction: (String) -> Unit,
    successAction: (String) -> Unit,
    finishAction: () -> Unit,
    receiveAction: (com.example.mitalk.ui.chat.ChatData) -> Unit,
    receiveActionUpdate: (com.example.mitalk.ui.chat.ChatData) -> Unit,
    receiveActionDelete: (String) -> Unit,
    errorAction: () -> Unit,
) {
    private lateinit var webSocket: WebSocket
    private lateinit var request: Request
    private lateinit var client: OkHttpClient
    private val listener: WebSocketListener
    private var roomId: String? = null

    init {
        listener = object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                val gson = Gson()
                when (gson.fromJson(text, SocketType::class.java).type) {
                    "SYSTEM_1_1_1", "SYSTEM_1_2" -> {
                        val result = gson.fromJson(text, WaitingRoom::class.java)
                        waitingAction(result.order)
                    }
                    "SYSTEM_1_1_2" -> {
                        gson.fromJson(text, FailWaitingRoom::class.java)
                        failAction()
                    }
                    "SYSTEM_3_1" -> {
                        val result = gson.fromJson(text, SuccessRoom::class.java)
                        roomId = result.roomId
                        successAction(result.name)
                    }
                    "SYSTEM_3_2" -> {
                        closeSocket()
                        finishAction()
                    }
                    null -> {
                        val result = gson.fromJson(text, ChatData::class.java)
                        when (result.chatMessageType) {
                            "SEND" -> {
                                receiveAction(result.toUseData())
                            }
                            "UPDATE" -> {
                                receiveActionUpdate(result.toUseData())
                            }
                            "DELETE" -> {
                                receiveActionDelete(result.messageId)
                            }
                        }
                    }
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                errorAction()
            }
        }
    }

    fun send(
        messageId: String? = null,
        text: String? = null,
        messageType: String = "SEND",
    ) {
        val data = JSONObject().apply {
            put("room_id", roomId)
            if (messageId != null) put("message_id", messageId)
            put("chat_message_type", messageType)
            put("role", "CUSTOMER")
            if (text != null) put("message", text)
        }

        webSocket.send(data.toString())
    }

    fun startSocket(chatType: String, accessToken: String, roomId: String = "null") {
        client = OkHttpClient()
        request = Request.Builder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("ChatType", chatType)
            .addHeader("RoomId", roomId)
            .url(BuildConfig.SOCKET_URL)
            .build()
        webSocket = client.newWebSocket(request, listener)
    }

    fun closeSocket() {
        webSocket.close(1000, "Close")
        client.dispatcher.executorService.shutdown()
    }
}