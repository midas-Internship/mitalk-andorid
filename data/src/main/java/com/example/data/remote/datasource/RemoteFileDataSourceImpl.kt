package com.example.data.remote.datasource

import com.example.data.remote.api.FileApi
import com.example.data.remote.response.toEntity
import com.example.data.remote.util.miTalkApiCall
import com.example.data.util.toMultiPartBody
import com.example.domain.entity.FileEntity
import java.io.File
import javax.inject.Inject

class RemoteFileDataSourceImpl @Inject constructor(
    private val fileApi: FileApi
) : RemoteFileDataSource {
    override suspend fun postFile(file: File): FileEntity = miTalkApiCall {
        fileApi.postFile(file.toMultiPartBody()).toEntity()
    }
}