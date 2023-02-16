package com.example.data.remote.api

import com.example.data.remote.response.FileResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileApi {
    @Multipart
    @POST("/file")
    fun postFile(
        @Part file: MultipartBody.Part
    ): FileResponse
}