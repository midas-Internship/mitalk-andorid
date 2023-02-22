package com.example.domain.usecase.chat

import com.example.domain.repository.ChatRepository
import javax.inject.Inject

class ClearChatInfoUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        chatRepository.clearChatInfo()
    }
}