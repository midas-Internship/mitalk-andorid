package com.example.data.local.system

interface LocalSystemDataSource {
    suspend fun saveLanguage(language: String)
    suspend fun fetchLanguage(): String
}