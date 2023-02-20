package com.example.mitalk.ui.record

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.R
import com.example.mitalk.mvi.RecordDetailState
import com.example.mitalk.ui.chat.ChatItem
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.theme.*
import com.example.mitalk.util.toChatTime
import com.example.mitalk.vm.record_detail.RecordDetailViewModel

@Composable
fun RecordDetailScreen(
    navController: NavController,
    headerId: Int,
    recordId: String,
    vm: RecordDetailViewModel = hiltViewModel()
) {
    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(Unit) {
        vm.getRecordDetail(recordId = recordId)
    }

    Column {
        MiHeader(
            backPressed = {
                navController.popBackStack()
            },
            text = stringResource(id = headerId),
        )
        Spacer(modifier = Modifier.height(10.dp))
        FindInput {
        }
        Box(modifier = Modifier.weight(1f)) {
            ChatList(state.messageRecords)
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
fun ChatList(
    chatList: List<RecordDetailState.MessageRecordData>,
    chatListState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        state = chatListState
    ) {
        items(1) {
            Spacer(modifier = Modifier.height(20.dp))
        }
        itemsIndexed(chatList) { _, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp),
                horizontalArrangement = if (item.sender == "CUSTOMER") Arrangement.End else Arrangement.Start
            ) {
                if (item.sender == "CUSTOMER") {
                    ClientChat(
                        item = item
                    )
                } else {
                    CounselorChat(item = item)
                }
            }
        }
        items(1) {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun CounselorChat(
    item: RecordDetailState.MessageRecordData,
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            painter = painterResource(id = MitalkIcon.Counselor.drawableId),
            contentDescription = MitalkIcon.Counselor.contentDescription,
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.Top),
        )
        Spacer(modifier = Modifier.width(3.dp))
        Column {
            Light09NO(text = stringResource(id = R.string.counselor))
            if (item.isDeleted) Bold11NO(text = stringResource(id = R.string.main_screen)) else {
                ChatItem(
                    item = item.dataMap.last().message,
                    isMe = item.sender == "CUSTOMER",
                    modifier = Modifier
                        .background(
                            color = MitalkColor.MainBlue,
                            shape = com.example.mitalk.ui.chat.CounselorChat
                        )
                        .widthIn(min = 0.dp, max = 180.dp)
                        .padding(horizontal = 7.dp, vertical = 5.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(3.dp))
        Light09NO(text = item.dataMap.last().time.toChatTime())
    }
}

@Composable
fun ClientChat(
    item: RecordDetailState.MessageRecordData
) {
    Box {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Light09NO(text = item.dataMap.last().time.toChatTime())
            Spacer(modifier = Modifier.width(3.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = MitalkColor.White,
                        shape = com.example.mitalk.ui.chat.ClientChat
                    )
                    .widthIn(min = 0.dp, max = 200.dp)
                    .padding(horizontal = 7.dp, vertical = 5.dp)
            ) {
                if (item.isDeleted) Bold11NO(text = stringResource(id = R.string.main_screen)) else {
                    ChatItem(item = item.dataMap.last().message)
                }
            }
        }
    }
}

@Composable
@Preview
fun showRecordDetailScreen() {
    val navController = rememberNavController()
    RecordDetailScreen(navController = navController, 0, "헤더")
}