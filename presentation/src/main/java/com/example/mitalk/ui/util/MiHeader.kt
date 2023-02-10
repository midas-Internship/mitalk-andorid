package com.example.mitalk.ui.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.MitalkIcon
import com.example.mitalk.util.theme.Regular14NO

@Composable
fun MiHeader(
    navController: NavController,
    modifier: Modifier = Modifier,
    backPressed: Boolean = true,
    btnText: String = "메인",
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
        if (backPressed) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.Start)
                    .miClickable(rippleEnabled = false) {
                        navController.popBackStack()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = modifier.width(16.dp))

                Icon(
                    painter = painterResource(id = MitalkIcon.Back.drawableId),
                    contentDescription = MitalkIcon.Back.contentDescription,
                    modifier = Modifier.miClickable(rippleEnabled = false) { navController.popBackStack() }
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

    val navController = rememberNavController()

    MiHeader(
        navController = navController,
        text = "상담 연결"
    )
}