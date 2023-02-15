package com.example.mitalk.ui.setting

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.Bold20NO
import java.util.Locale

@Composable
fun SettingScreen(
    navController: NavController
) {
    val context = LocalContext.current
    Column {
        MiHeader(backPressed = {
            navController.popBackStack()
        })
        Bold20NO(text = "영어", modifier = Modifier.miClickable {
            changeLanguage("en", context)
        })
        Bold20NO(text = "한국어", modifier = Modifier.miClickable {
            changeLanguage("kr", context)
        })
    }
}

fun changeLanguage(language: String, context: Context) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}