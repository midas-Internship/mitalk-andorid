package com.example.domain.usecase.record

import com.example.domain.repository.RecordRepository
import javax.inject.Inject

class GetRecordListUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        recordRepository.getRecordList()
    }
}