package com.example.mitalk.util

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalTime.toChatTime(): String {
    return this.format(DateTimeFormatter.ofPattern("a HH:mm"))
}

fun String.toChatTime(): String {
    val time = LocalDateTime.parse(this)
    return time.format(DateTimeFormatter.ofPattern("a HH:mm"))
}

fun String.toRecordDate(): String =
    "${substring(0..3)}.${substring(5..6)}.${substring(8..9)}"