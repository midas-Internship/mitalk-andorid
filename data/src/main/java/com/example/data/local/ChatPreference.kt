package com.example.data.local

interface ChatPreference {
    suspend fun saveChatType(chatType: String)

    suspend fun fetchChatType(): String

    suspend fun clearChatType()

    suspend fun saveRoomId(roomId: String)

    suspend fun fetchRoomId(): String

    suspend fun clearRoomId()
}