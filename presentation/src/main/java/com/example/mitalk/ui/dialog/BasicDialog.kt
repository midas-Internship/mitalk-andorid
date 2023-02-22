package com.example.mitalk.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mitalk.R
import com.example.mitalk.ui.util.OkayEntireShape
import com.example.mitalk.ui.util.OkayShape
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.Bold20NO
import com.example.mitalk.util.theme.MitalkColor
import com.example.mitalk.util.theme.Regular12NO
import com.example.mitalk.util.theme.Regular14NO

@Composable
fun BasicDialog(
    visible: Boolean,
    title: String,
    content: String,
    onDismissRequest: () -> Unit,
    onBtnPressed: (() -> Unit)? = null
) {
    if (visible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .width(280.dp)
                    .height(157.dp)
                    .background(color = MitalkColor.White, shape = RoundedCornerShape(5.dp))
            ) {
                Bold20NO(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                )
                Regular12NO(
                    text = content,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    color = Color(0xFF4C4C4C)
                )
                Spacer(modifier = Modifier.weight(1F))
                BasicDialogBtn(onDismissRequest = onDismissRequest, onBtnPressed = onBtnPressed)
            }
        }
    }
}

@Composable
fun BasicDialogBtn(
    onDismissRequest: () -> Unit,
    onBtnPressed: (() -> Unit)?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        if (onBtnPressed != null) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1F)
                    .fillMaxHeight()
                    .background(
                        color = Color(0xFFD0D1DB),
                        shape = RoundedCornerShape(bottomStart = 5.dp)
                    )
                    .miClickable { onDismissRequest() }
            ) {
                Regular14NO(text = stringResource(id = R.string.cancel))
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .background(
                    color = Color(0xFF4C53FF),
                    shape = if (onBtnPressed != null) OkayShape else OkayEntireShape
                )
                .miClickable { if (onBtnPressed != null) onBtnPressed() else onDismissRequest() }
        ) {
            Regular14NO(text = stringResource(id = R.string.okay), color = MitalkColor.White)
        }
    }
}

@Composable
@Preview
fun showBasicDialog() {
    BasicDialog(visible = true, onDismissRequest = { }, title = "제목", content = "본문") {

    }
}