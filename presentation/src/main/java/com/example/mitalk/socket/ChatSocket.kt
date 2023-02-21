package com.example.mitalk.socket

import com.example.mitalk.BuildConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.*
import org.json.JSONObject
import java.util.UUID

class ChatSocket(
    failAction: () -> Unit,
    startAction: () -> Unit,
    waitingAction: (String) -> Unit,
    successAction: (String) -> Unit,
    finishAction: () -> Unit,
    receiveAction: (com.example.mitalk.ui.chat.ChatData) -> Unit,
    receiveActionUpdate: (com.example.mitalk.ui.chat.ChatData) -> Unit,
    receiveActionDelete: (String) -> Unit,
) {
    private lateinit var webSocket: WebSocket
    private lateinit var request: Request
    private lateinit var client: OkHttpClient
    private val listener: WebSocketListener

    init {
        listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                startAction()
            }

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
                        successAction(result.roomId)
                    }
                    "SYSTEM_3_2" -> {
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
                println("소켓 연결 안됨 $t")
            }
        }
    }

    fun send(
        roomId: String,
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

    fun startSocket(chatType: String, accessToken: String) {
        client = OkHttpClient()
        request = Request.Builder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("ChatType", chatType)
            .url(BuildConfig.SOCKET_URL)
            .build()
        webSocket = client.newWebSocket(request, listener)
    }

    fun close() {
        webSocket?.close(1000, "Close")
        client.dispatcher.executorService.shutdown()
    }
}