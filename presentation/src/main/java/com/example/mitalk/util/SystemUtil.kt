package com.example.mitalk.util

import android.content.Context
import android.content.res.Configuration
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