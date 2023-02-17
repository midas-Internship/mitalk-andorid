package com.example.data.repository

import com.example.data.local.system.LocalSystemDataSource
import com.example.domain.repository.SystemRepository
import javax.inject.Inject

class SystemRepositoryImpl @Inject constructor(
    private val localSystemDataSource: LocalSystemDataSource
) : SystemRepository {
    override suspend fun saveLanguage(language: String) =
        localSystemDataSource.saveLanguage(language)

    override suspend fun fetchLanguage(): String =
        localSystemDataSource.fetchLanguage()
}