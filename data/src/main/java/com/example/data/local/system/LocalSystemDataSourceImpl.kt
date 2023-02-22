package com.example.data.local.system

import com.example.data.local.SystemPreference
import javax.inject.Inject

class LocalSystemDataSourceImpl @Inject constructor(
    private val systemPreference: SystemPreference
) : LocalSystemDataSource {
    override suspend fun saveLanguage(language: String) =
        systemPreference.saveLanguage(language)

    override suspend fun fetchLanguage(): String =
        systemPreference.fetchLanguage()
}