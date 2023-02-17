package com.example.data.local

interface SystemPreference {
    suspend fun saveLanguage(language: String)

    suspend fun fetchLanguage(): String
}