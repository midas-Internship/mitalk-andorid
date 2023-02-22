package com.example.domain.repository

import com.example.domain.entity.ChatInfoEntity

interface ChatRepository {
    suspend fun saveChatInfo(chatInfoEntity: ChatInfoEntity)

    suspend fun fetchChatInfo(): ChatInfoEntity

    suspend fun clearChatInfo()
}