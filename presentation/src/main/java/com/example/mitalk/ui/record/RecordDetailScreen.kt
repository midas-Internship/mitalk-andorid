package com.example.mitalk.ui.record

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.R
import com.example.mitalk.mvi.RecordDetailSideEffect
import com.example.mitalk.mvi.RecordDetailState
import com.example.mitalk.ui.chat.ChatItem
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.observeWithLifecycle
import com.example.mitalk.util.theme.*
import com.example.mitalk.util.toChatTime
import com.example.mitalk.vm.record_detail.RecordDetailViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
@Composable
fun RecordDetailScreen(
    navController: NavController,
    headerId: Int,
    recordId: String,
    vm: RecordDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val chatListState = rememberLazyListState()
    var text by remember { mutableStateOf("") }

    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(Unit) {
        vm.getRecordDetail(recordId = recordId)
    }

    sideEffect.observeWithLifecycle {
        when (it) {
            is RecordDetailSideEffect.ChangeCurrentFindPosition -> {
                MainScope().launch {
                    chatListState.scrollToItem(it.list[it.scrollPosition])
                }
            }
        }
    }

    Column {
        MiHeader(
            backPressed = {
                navController.popBackStack()
            },
            text = stringResource(id = headerId),
        )
        Spacer(modifier = Modifier.height(10.dp))
        FindInput(
            text = text,
            isFind = state.totalFindResultList.isNotEmpty(),
            currentResult = state.currentFindPosition + 1,
            totalResult = state.totalFindResultList,
            onTextChange = { text = it },
            onFindAction = {
                val list = state.messageRecords.mapIndexed { index, data ->
                    if (!data.isDeleted && data.dataMap.last().message.contains(text)) index else null
                }.filterNotNull().toMutableList()
                vm.setTotalFindResultList(list)
                if (list.isEmpty()) {
                    Toast.makeText(context, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    focusManager.clearFocus()
                }
            }, onCancelAction = {
                vm.clearFindResult()
                text = ""
            }, upFindAction = {
                if (state.currentFindPosition < state.totalFindResultList.size - 1) {
                    vm.plusCurrentFindPosition()
                }
            }, downFindAction = {
                if (state.currentFindPosition > 0) {
                    vm.minusCurrentFindPosition()
                }
            })
        Box(modifier = Modifier.weight(1f)) {
            ChatList(chatList = state.messageRecords, chatListState = chatListState)
        }
    }
}

@Composable
fun FindInput(
    text: String,
    isFind: Boolean,
    currentResult: Int,
    totalResult: List<Int>,
    onTextChange: (String) -> Unit,
    onFindAction: () -> Unit,
    onCancelAction: () -> Unit,
    downFindAction: () -> Unit,
    upFindAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(30.dp)
            .padding(horizontal = 20.dp)
            .background(color = MitalkColor.White, shape = RoundedCornerShape(5.dp))
            .border(width = 1.dp, color = MitalkColor.MainBlue, shape = RoundedCornerShape(5.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = "$text${if (isFind) " $currentResult/${totalResult.size}" else ""}",
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 15.dp, vertical = 5.dp),
            enabled = !isFind,
            onValueChange = { onTextChange(it) },
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
        if (!isFind) {
            Icon(
                painter = painterResource(id = MitalkIcon.Search.drawableId),
                contentDescription = MitalkIcon.Search.contentDescription,
                modifier = Modifier
                    .width(15.dp)
                    .miClickable(rippleEnabled = false) {
                        if (!text.isNullOrBlank()) {
                            onFindAction()
                        }
                    }
            )
        } else {
            Icon(
                painter = painterResource(id = MitalkIcon.Up.drawableId),
                contentDescription = MitalkIcon.Up.contentDescription,
                modifier = Modifier
                    .width(15.dp)
                    .miClickable(rippleEnabled = false) {
                        upFindAction()
                    }
            )
            Icon(
                painter = painterResource(id = MitalkIcon.Down.drawableId),
                contentDescription = MitalkIcon.Down.contentDescription,
                modifier = Modifier
                    .width(15.dp)
                    .miClickable(rippleEnabled = false) {
                        downFindAction()
                    }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                painter = painterResource(id = MitalkIcon.Cancel.drawableId),
                contentDescription = MitalkIcon.Cancel.contentDescription,
                modifier = Modifier
                    .width(15.dp)
                    .miClickable(rippleEnabled = false) {
                        onCancelAction()
                    }
            )
        }
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
            if (item.isDeleted) Bold11NO(text = stringResource(id = R.string.delete_message)) else {
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
                if (item.isDeleted) Bold11NO(text = stringResource(id = R.string.delete_message)) else {
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