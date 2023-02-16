package com.example.data.repository

import com.example.data.remote.datasource.RemoteFileDataSource
import com.example.domain.entity.FileEntity
import com.example.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val remoteFileDataSource: RemoteFileDataSource
) : FileRepository {
    override suspend fun postFile(file: File): FileEntity =
        remoteFileDataSource.postFile(file)
}