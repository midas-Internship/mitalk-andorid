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
import com.example.mitalk.util.theme.Bold13NO
import com.example.mitalk.util.theme.Bold26NO
import com.example.mitalk.util.theme.Light09NO
import com.example.mitalk.util.theme.MitalkColor

@Composable
fun CrowedDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit
) {
    if (visible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Box(
                modifier = Modifier
                    .width(280.dp)
                    .height(160.dp)
                    .background(color = MitalkColor.White, shape = RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Bold13NO(
                        text = stringResource(id = R.string.crowed_service),
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Light09NO(
                        text = stringResource(id = R.string.crowed_service_comment),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun previewCrowedDialog() {
    CrowedDialog(visible = true) {

    }
}