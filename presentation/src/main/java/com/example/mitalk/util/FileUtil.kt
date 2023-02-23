package com.example.mitalk.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.mitalk.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files

@SuppressLint("Range")
fun Uri.toFile(context: Context, approve: Boolean): File {
    val fileName = getFileName(context)
    val file = createTempFile(context, fileName)
    copyToFile(context, this, file)

    return file.absolutePath.replace(".x-hwp", "").check(approve = approve)
}

fun String.isFile(): Boolean = contains(BuildConfig.IMAGE_URL)

private fun Uri.getFileName(context: Context): String {
    val name = this.toString().split("/").last()
    val ext = context.contentResolver.getType(this)!!.split("/").last()
    return "$name.$ext"
}

private fun String.check(approve: Boolean): File {
    val file = File(this)
    val kb = file.length() / 1024
    val mb = kb / 1024
    if (mb > 1000) {
        throw FileOverException()
    } else if (mb > 100 && !approve) {
        throw FileSizeException()
    } else if (!(ImageAllowedList + VideoAllowedList + DocumentAllowedList).contains(
            split(".").last().lowercase()
        )
    ) {
        throw FileNotAllowedException()
    }
    return file
}

private fun createTempFile(context: Context, fileName: String): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File(storageDir, fileName)
}

private fun copyToFile(context: Context, uri: Uri, file: File) {
    val inputStream = context.contentResolver.openInputStream(uri)
    val outputStream = FileOutputStream(file)

    val buffer = ByteArray(4 * 1024)
    while (true) {
        val byteCount = inputStream!!.read(buffer)
        if (byteCount < 0) break
        outputStream.write(buffer, 0, byteCount)
    }

    outputStream.flush()
}

val ImageAllowedList = listOf(
    "jpg", "jpeg", "gif", "png", "bmp", "svg"
)

val VideoAllowedList = listOf(
    "mp4", "mov", "wmv", "avi", "mkv", "mpeg-2"
)

val DocumentAllowedList = listOf(
    "hwp", "txt", "doc", "pdf", "csv", "xls", "ppt", "pptx"
)

class FileSizeException() : RuntimeException()
class FileOverException() : RuntimeException()
class FileNotAllowedException() : RuntimeException()