package com.example.mitalk.ui.theme.sample.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mitalk.ui.theme.sample.mvi.SampleEffect
import com.example.mitalk.ui.theme.sample.vm.SampleViewModel
import com.example.mitalk.util.observeWithLifecycle
import com.example.mitalk.util.rememberToast
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun SampleScreen(
    // viewModel: SampleViewModel = hiltViewModel()
) {

    val toast = rememberToast()

//    val container = viewModel.container
//    val state = container.stateFlow.collectAsState().value
//    val sideEffect = container.sideEffectFlow

    LaunchedEffect(Unit) {
        // 시작하면서 한번만 호출하고 싶을때 이런식으로 사용
        // viewModel.checkCount(state.clickCount)
    }

//    sideEffect.observeWithLifecycle {
//        when (it) {
//            SampleEffect.CannotCountClickCount -> toast(message = "클릭 입력 횟수를 확인할 수 없습니다")
//            SampleEffect.TooManyClickCount -> toast(message = "클릭 수가 너무 많습니다")
//            SampleEffect.UnknownException -> toast(message = "알 수 없는 오류입니다")
//        }
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
            .size(50.dp)
            .clickable {
                //      viewModel.inputClickCount(state.clickCount)
                //  viewModel.checkCount(state.clickCount)
            },
    ) {
//        Text(text = state.returnCount)
    }
}

@Composable
@Preview(showBackground = true)
fun ShowSampleScreen() {
    SampleScreen()
}
