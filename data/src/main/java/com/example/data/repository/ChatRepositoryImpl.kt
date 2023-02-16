package com.example.data.repository

import com.example.data.local.chat.LocalChatDataSource
import com.example.domain.entity.ChatInfoEntity
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val localChatDataSource: LocalChatDataSource
) : ChatRepository {
    override suspend fun saveChatInfo(chatInfoEntity: ChatInfoEntity) =
        localChatDataSource.saveChatInfo(chatInfoEntity)

    override suspend fun fetchChatInfo(): ChatInfoEntity =
        localChatDataSource.fetchChatInfo()

    override suspend fun clearChatInfo() =
        localChatDataSource.clearChatInfo()
}