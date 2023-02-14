package com.example.mitalk.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mitalk.R
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.Bold20NO
import com.example.mitalk.util.theme.MitalkColor
import com.example.mitalk.util.theme.Regular12NO
import com.example.mitalk.util.theme.Regular14NO
import kotlinx.coroutines.delay

@Composable
fun EmptyDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onTimeOut: () -> Unit
) {
    var remainTime by remember { mutableStateOf(30) }
    LaunchedEffect(Unit) {
        while (remainTime != 0) {
            delay(1_000L)
            remainTime--
        }
        onTimeOut()
    }
    if (visible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .width(280.dp)
                    .height(157.dp)
                    .background(color = MitalkColor.White, shape = RoundedCornerShape(5.dp))
            ) {
                Bold20NO(
                    text = stringResource(id = R.string.empty),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                )
                Regular12NO(
                    text = stringResource(id = R.string.empty_comment),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 5.dp, end = 5.dp),
                    color = Color(0xFF4C4C4C)
                )
                Spacer(modifier = Modifier.weight(1F))
                EmptyBtn(remainTime = remainTime, onDismissRequest = onDismissRequest)
            }
        }
    }
}

@Composable
fun EmptyBtn(
    remainTime: Int,
    onDismissRequest: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(bottomStart = 5.dp)
                )
        ) {
            Row {
                Regular14NO(text = stringResource(id = R.string.remain_time))
                Regular14NO(text = remainTime.toString(), color = if (remainTime > 5) MitalkColor.Black else Color(0xFFFC2F2F))
                Regular14NO(text = stringResource(id = R.string.remain_tim_sec))
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .background(
                    color = Color(0xFF4C53FF),
                    shape = RoundedCornerShape(bottomEnd = 5.dp)
                )
                .miClickable { onDismissRequest }
        ) {
            Regular14NO(text = stringResource(id = R.string.okay), color = MitalkColor.White)
        }
    }
}

@Composable
@Preview
fun showEmptyDialog() {
    EmptyDialog(visible = true, onDismissRequest = { }) {
    }
}