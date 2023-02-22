package com.example.mitalk.util

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever

//영상에서 썸네일 추출 하는 함수
fun String.toThumbnail(context: Context): Bitmap? {
    val mediaMetadataRetriever = MediaMetadataRetriever()
    kotlin.runCatching {
        mediaMetadataRetriever.setDataSource(this)
        mediaMetadataRetriever.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST)
    }.onSuccess {
        mediaMetadataRetriever.release()
        return it
    }
    return null
}