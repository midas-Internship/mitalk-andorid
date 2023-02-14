package com.example.mitalk.ui.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.MitalkIcon
import com.example.mitalk.util.theme.Regular14NO
import com.example.mitalk.R

@Composable
fun MiHeader(
    modifier: Modifier = Modifier,
    backBtn: Boolean = true,
    backPressed: () -> Unit = {},
    btnText: String = stringResource(id = R.string.main),
    text: String? = null,
    content: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .bottomBorder(
                strokeWidth = 1.dp,
                color = Color(0xFFD8D8D8),
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (backBtn) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.Start)
                    .miClickable(rippleEnabled = false) {
                        backPressed()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = modifier.width(16.dp))

                Icon(
                    painter = painterResource(id = MitalkIcon.Back.drawableId),
                    contentDescription = MitalkIcon.Back.contentDescription,
                    modifier = Modifier.miClickable(rippleEnabled = false) { backPressed() }
                )

                Spacer(modifier = Modifier.width(4.dp))

                Regular14NO(text = btnText)
            }
        }

        if (content != null) {
            content()
        }

        if (text != null) {
            Regular14NO(text = text)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowHeader() {
    MiHeader(
        text = "상담 연결"
    )
}