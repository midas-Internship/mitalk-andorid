package com.example.data.sample

import com.example.domain.sample.SampleEntity

interface SampleDataSource {

    suspend fun checkCount(clickCount: Int): SampleEntity
}