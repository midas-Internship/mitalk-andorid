package com.example.mitalk.ui.chat

import com.example.mitalk.BuildConfig
import okhttp3.*

class ChatClient(receiveAction: (String) -> Unit) {
    private var webSocket: WebSocket
    private val client by lazy { OkHttpClient() }

    init {
        val request = Request.Builder()
            .url(BuildConfig.SOCKET_URL)
            .build()
        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                receiveAction(text)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
            }
        }
        webSocket = client.newWebSocket(request, listener)
    }

    fun close() {
        webSocket.close(1000, "Close")
        client.dispatcher.executorService.shutdown()
    }

    fun send(text: String) {
        webSocket.send(text)
    }
}