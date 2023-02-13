package com.example.domain.usecase

import javax.inject.Inject

class QuestionUseCase @Inject constructor(

) {
    suspend operator fun invoke() = kotlin.runCatching {
        ""
    }
}