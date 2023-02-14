package com.example.domain.usecase.record

import com.example.domain.repository.RecordRepository
import javax.inject.Inject

class GetRecordDetailUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    suspend operator fun invoke(recordId: String) = kotlin.runCatching {
        recordRepository.getRecordDetail(recordId)
    }
}