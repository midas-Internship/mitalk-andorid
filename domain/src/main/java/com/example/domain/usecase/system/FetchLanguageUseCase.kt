package com.example.domain.usecase.system

import com.example.domain.repository.SystemRepository
import javax.inject.Inject

class FetchLanguageUseCase @Inject constructor(
    private val systemRepository: SystemRepository
){
    suspend operator fun invoke() = kotlin.runCatching {
        systemRepository.fetchLanguage()
    }
}