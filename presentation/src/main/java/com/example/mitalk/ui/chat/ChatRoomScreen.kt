package com.example.mitalk.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.Bold11NO
import com.example.mitalk.util.theme.MiTalkTypography
import com.example.mitalk.util.theme.MitalkIcon
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Stable
private val CounselorChat =
    RoundedCornerShape(topStart = 0.dp, topEnd = 5.dp, bottomEnd = 5.dp, bottomStart = 5.dp)
private val ClientChat =
    RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomEnd = 0.dp, bottomStart = 5.dp)

@Composable
fun ChatRoomScreen(
    navController: NavController
) {
    var chatList = remember { mutableStateListOf<String>() }
    var chatListState = rememberLazyListState()

    Column {
        MiHeader(navController = navController, modifier = Modifier.background(Color(0xFFF2F2F2)))
        Box(modifier = Modifier.weight(1f)) {
            ChatList(chatList = listOf(), chatListState = chatListState)
        }
        ChatInput(sendAction = {
            chatList.add(it)
            MainScope().launch {
                chatListState.scrollToItem(chatList.size - 1)
            }
        })
        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Composable
fun ChatList(chatList: List<String>, chatListState: LazyListState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        state = chatListState
    ) {
        itemsIndexed(chatList) { _, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = if (true) Arrangement.End else Arrangement.Start
            ) {
                Bold11NO(
                    text = item,
                    modifier = Modifier
                        .background(
                            color = if (true) Color.Blue else Color.Gray,
                            shape = if (true) ClientChat else CounselorChat
                        )
                        .padding(horizontal = 7.dp, vertical = 5.dp)
                )
            }
        }
    }
}

@Composable
fun ChatInput(
    sendAction: (String) -> Unit
) {
    var isExpand by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(7.dp))
        if (isExpand) {
            IconButton(icon = MitalkIcon.Cancel) {
                isExpand = false
            }
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(icon = MitalkIcon.Picture) {
            }
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(icon = MitalkIcon.Video) {
            }
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(icon = MitalkIcon.Document) {
            }
        } else {
            IconButton(icon = MitalkIcon.Plus) {
                isExpand = true
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(Color.White),
            textStyle = MiTalkTypography.regular12NO
        )
        IconButton(icon = MitalkIcon.Send) {
            if (!text.isNullOrBlank()) {
                sendAction(text)
            }
            text = ""
        }
    }
}

@Composable
fun IconButton(
    icon: MitalkIcon,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.width(20.dp)) {
        Icon(
            painter = painterResource(id = icon.drawableId),
            contentDescription = icon.contentDescription,
            modifier = Modifier
                .fillMaxHeight()
                .miClickable {
                    onClick()
                }
        )
    }
}

@Composable
@Preview
fun showChatRoomScreen() {
    val navController = rememberNavController()
    ChatRoomScreen(navController)
}