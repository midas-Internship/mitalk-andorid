package com.example.domain.usecase.chat

import com.example.domain.entity.ChatInfoEntity
import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class SaveChatInfoUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(chatInfoEntity: ChatInfoEntity) = kotlin.runCatching {
        chatRepository.saveChatInfo(chatInfoEntity)
    }
}