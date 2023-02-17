package com.example.mitalk.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mitalk.R
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.English
import com.example.mitalk.util.Korean
import com.example.mitalk.util.changeLanguage
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.Bold20NO
import com.example.mitalk.vm.setting.SettingViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    vm: SettingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Column {
        MiHeader(backPressed = {
            navController.popBackStack()
        })
        Bold20NO(text = stringResource(id = R.string.english), modifier = Modifier.miClickable {
            vm.saveLanguage(English)
            English.changeLanguage(context)
        })
        Bold20NO(text = stringResource(id = R.string.korean), modifier = Modifier.miClickable {
            vm.saveLanguage(Korean)
            Korean.changeLanguage(context)
        })
    }
}