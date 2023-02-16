package com.example.domain.repository

import com.example.domain.entity.FileEntity
import java.io.File

interface FileRepository {
    suspend fun postFile(file: File): FileEntity
}