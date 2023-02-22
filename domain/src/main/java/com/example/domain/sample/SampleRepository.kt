package com.example.domain.sample

interface SampleRepository {

    suspend fun checkCount(clickCount: Int): SampleEntity
}