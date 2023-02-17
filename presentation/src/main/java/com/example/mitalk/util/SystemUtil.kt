package com.example.mitalk.util

import android.content.Context
import android.content.res.Configuration
import com.example.mitalk.R
import java.util.*

const val English = "en"
const val Korean = "kr"

fun String.changeLanguage(context: Context) {
    val locale = Locale(this)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}

fun String.changeLanguageId(): Int =
    when (this) {
        English -> R.string.english
        Korean -> R.string.korean
        else -> R.string.korean
    }