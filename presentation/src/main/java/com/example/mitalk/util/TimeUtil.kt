package com.example.mitalk.util

fun String.toRecordDate(): String =
    "${substring(0..3)}.${substring(5..6)}.${substring(8..9)}"