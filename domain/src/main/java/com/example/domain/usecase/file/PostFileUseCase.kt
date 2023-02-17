package com.example.domain.usecase.file

import com.example.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class PostFileUseCase @Inject constructor(
    private val fileRepository: FileRepository
) {
    suspend operator fun invoke(file: File) = kotlin.runCatching {
        fileRepository.postFile(file)
    }
}