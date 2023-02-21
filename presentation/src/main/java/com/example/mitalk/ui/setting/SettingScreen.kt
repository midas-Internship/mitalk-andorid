package com.example.mitalk.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mitalk.R
import com.example.mitalk.ui.dialog.LanguageDialog
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.*
import com.example.mitalk.util.theme.Bold13NO
import com.example.mitalk.util.theme.Bold20NO
import com.example.mitalk.vm.setting.SettingViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    vm: SettingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    var settingLanguageVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        vm.fetchLanguage()
    }

    Column {
        MiHeader(backPressed = {
            navController.popBackStack()
        })
        Row(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(top = 30.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(5.dp))
                .miClickable {
                    settingLanguageVisible = true
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 15.dp)
            ) {
                Bold20NO(text = stringResource(id = R.string.setting_language))
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 15.dp)
            ) {
                Bold13NO(
                    text = stringResource(id = state.language.changeLanguageId())
                )
            }
        }
        LanguageDialog(visible = settingLanguageVisible, onDismissRequest = {
            settingLanguageVisible = false
        }, onChangeAction = {
            it.changeLanguage(context)
            vm.saveLanguage(it)
            vm.fetchLanguage()
        })
    }
}