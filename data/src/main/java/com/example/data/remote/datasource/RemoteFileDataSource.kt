package com.example.data.remote.datasource

import com.example.domain.entity.FileEntity
import java.io.File

interface RemoteFileDataSource {
    suspend fun postFile(file: File): FileEntity
}