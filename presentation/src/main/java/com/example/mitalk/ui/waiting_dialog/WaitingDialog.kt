package com.example.mitalk.ui.waiting_dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mitalk.R
import com.example.mitalk.ui.util.bottomBorder
import com.example.mitalk.util.theme.Bold13NO
import com.example.mitalk.util.theme.Light09NO
import com.example.mitalk.util.theme.MitalkColor

@Composable
fun WaitingDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit
) {
    if (visible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .width(280.dp)
                    .height(200.dp)
                    .background(color = MitalkColor.White, shape = RoundedCornerShape(5.dp))
            ) {
                Bold13NO(
                    text = stringResource(id = R.string.waiting_service),
                    modifier = Modifier.padding(top = 25.dp, start = 17.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 43.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Bold13NO(text = stringResource(id = R.string.waiting_service_prev_comment))
                    Bold13NO(
                        text = "12",
                        color = Color(0xA829C18B),
                        modifier = Modifier
                            .bottomBorder(
                                strokeWidth = 1.dp,
                                color = Color(0xA829C18B)
                            ),
                        textAlign = TextAlign.Center
                    )
                    Bold13NO(text = stringResource(id = R.string.waiting_service_next_comment))
                }
                Light09NO(
                    text = stringResource(id = R.string.waiting_service_comment),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowMainScreen() {
    WaitingDialog(visible = true, onDismissRequest = { })
}