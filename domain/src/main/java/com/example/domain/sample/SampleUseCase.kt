package com.example.domain.sample

import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val repository: SampleRepository,
) {

    suspend operator fun invoke(
        clickCount: Int,
    ) = kotlin.runCatching {
        repository.checkCount(
            clickCount = clickCount,
        )
    }
}