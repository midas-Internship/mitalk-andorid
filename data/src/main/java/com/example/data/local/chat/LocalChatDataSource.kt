package com.example.data.local.chat

import com.example.domain.entity.ChatInfoEntity

interface LocalChatDataSource {
    suspend fun saveChatInfo(chatInfoEntity: ChatInfoEntity)

    suspend fun fetchChatInfo(): ChatInfoEntity

    suspend fun clearChatInfo()
}