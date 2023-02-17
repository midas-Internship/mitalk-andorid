package com.example.domain.repository

interface SystemRepository {
    suspend fun saveLanguage(language: String)
    suspend fun fetchLanguage(): String
}