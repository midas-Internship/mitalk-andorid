package com.example.domain.usecase.system

import com.example.domain.repository.SystemRepository
import javax.inject.Inject

class SaveLanguageUseCase @Inject constructor(
    private val systemRepository: SystemRepository
) {
    suspend operator fun invoke(language: String) = kotlin.runCatching {
        systemRepository.saveLanguage(language)
    }
}