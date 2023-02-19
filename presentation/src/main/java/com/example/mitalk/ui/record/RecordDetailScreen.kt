package com.example.mitalk.ui.record

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.R
import com.example.mitalk.ui.chat.ChatData
import com.example.mitalk.ui.chat.ChatList
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.theme.*

@Composable
fun RecordDetailScreen(
    navController: NavController,
    header: String,
) {
    var chatList = remember { mutableStateListOf<ChatData>() }
    Column {
        MiHeader(
            backPressed = {
                navController.popBackStack()
            },
            text = header,
        )
        Spacer(modifier = Modifier.height(10.dp))
        FindInput {
        }
        Box(modifier = Modifier.weight(1f)) {
        }
    }
}

@Composable
fun FindInput(
    onFindAction: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .height(30.dp)
            .padding(horizontal = 20.dp)
            .background(color = MitalkColor.White, shape = RoundedCornerShape(5.dp))
            .border(width = 1.dp, color = MitalkColor.MainBlue, shape = RoundedCornerShape(5.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text,
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 15.dp, vertical = 5.dp),
            onValueChange = { text = it },
            textStyle = MiTalkTypography.regular14NO,
            singleLine = true,
            decorationBox = @Composable {
                Box(
                    contentAlignment = Alignment.CenterStart,
                ) {
                    it()
                    if (text.isEmpty()) {
                        Regular12NO(
                            text = stringResource(id = R.string.find_comment_hit),
                            color = Color(0xFFACABED)
                        )
                    }
                }
            }
        )
        Icon(
            painter = painterResource(id = MitalkIcon.Search.drawableId),
            contentDescription = MitalkIcon.Search.contentDescription,
            modifier = Modifier
                .width(15.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
    }
}

@Composable
@Preview
fun showRecordDetailScreen() {
    val navController = rememberNavController()
    RecordDetailScreen(navController = navController, "헤더")
}