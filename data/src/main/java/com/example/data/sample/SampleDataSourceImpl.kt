package com.example.data.sample

import com.example.domain.sample.SampleEntity
import javax.inject.Inject

class SampleDataSourceImpl @Inject constructor(

): SampleDataSource {
    override suspend fun checkCount(clickCount: Int): SampleEntity =
        SampleEntity(clickCount.toString())
}