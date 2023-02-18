package com.example.mitalk.util

import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalTime.toChatTime(): String {
    return this.format(DateTimeFormatter.ofPattern("a HH:mm"))
}