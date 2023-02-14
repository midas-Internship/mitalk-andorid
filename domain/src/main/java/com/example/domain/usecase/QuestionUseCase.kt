package com.example.domain.usecase

import com.example.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionUseCase @Inject constructor(
    private val questionRepository: QuestionRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        questionRepository.getQuestionList()
    }
}