package com.example.data.sample

import com.example.domain.sample.SampleEntity
import com.example.domain.sample.SampleRepository
import java.util.concurrent.atomic.DoubleAdder
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(
    private val sampleDataSource: SampleDataSource,
) : SampleRepository {

    override suspend fun checkCount(
        clickCount: Int
    ): SampleEntity = sampleDataSource.checkCount(
        clickCount = clickCount
    )
}