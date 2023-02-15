package com.example.mitalk.socket

import com.example.mitalk.BuildConfig
import com.google.gson.Gson
import okhttp3.*

data class SocketType(
    val type: String?
)

data class FailWaitingRoom(
    val message: String
)

data class WaitingRoom(
    val order: String,
    val message: String
)

data class SuccessRoom(
    val roomId: String,
)

class ChatTypeSocket(
    failAction: () -> Unit = {},
    waitingAction: (String) -> Unit = {},
    successAction: (String) -> Unit = {},
    receiveAction: (String) -> Unit = {}
) {
    private lateinit var webSocket: WebSocket
    private lateinit var request: Request
    private val client by lazy { OkHttpClient() }
    private val listener: WebSocketListener

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
                        successAction(result.roomId)
                    }
                    null -> {
                        receiveAction(text)
                    }
                }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
            }
        }
    }

    fun send(text: String) {
        webSocket.send(text)
    }

    fun startSocket(chatType: String, accessToken: String) {
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