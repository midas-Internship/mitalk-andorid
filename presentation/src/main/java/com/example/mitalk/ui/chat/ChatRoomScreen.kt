package com.example.mitalk.ui.chat

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mitalk.R
import com.example.mitalk.mvi.ChatSideEffect
import com.example.mitalk.ui.dialog.EmptyDialog
import com.example.mitalk.ui.dialog.BasicDialog
import com.example.mitalk.ui.util.ClientChatShape
import com.example.mitalk.ui.util.CounselorChatShape
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.ui.util.TriangleShape
import com.example.mitalk.util.*
import com.example.mitalk.util.theme.*
import com.example.mitalk.util.theme.base.MitalkTheme
import com.example.mitalk.video.VideoPlayer
import com.example.mitalk.vm.chat.ChatViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

const val EmptyTime = 300

data class ChatData(
    val id: String,
    val text: String,
    val isMe: Boolean,
    val time: LocalTime,
)

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ChatRoomScreen(
    navController: NavController,
    roomId: String,
    vm: ChatViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val chatListState = rememberLazyListState()
    val inputFocusRequester = remember { FocusRequester() }
    var fileExceptionOnBtnPressed by remember { mutableStateOf<(() -> Unit)?>(null) }
    var editMsgId by remember { mutableStateOf<String?>(null) }
    var exitChatDialogVisible by remember { mutableStateOf(false) }
    var emptyDialogVisible by remember { mutableStateOf(false) }
    var fileExceptionDialogVisible by remember { mutableStateOf(false) }
    var selectItemUUID by remember { mutableStateOf<String?>(null) }
    var emptyTime by remember { mutableStateOf(EmptyTime) }
    var text by remember { mutableStateOf("") }
    var fileExceptionTitleId by remember { mutableStateOf(R.string.big_size_file) }
    var fileExceptionContentId by remember { mutableStateOf(R.string.big_size_file_comment) }
    val deleteMsg = stringResource(id = R.string.delete_message)

    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(emptyTime, exitChatDialogVisible) {
        if (!exitChatDialogVisible) {
            if (emptyTime > 0) {
                delay(1_000L)
                emptyTime--
            } else {
                emptyDialogVisible = true
            }
        }
    }

    sideEffect.observeWithLifecycle { effect ->
        when (effect) {
            ChatSideEffect.FileOverException -> {
                fileExceptionTitleId = R.string.over_size_file
                fileExceptionContentId = R.string.over_size_file_comment
                fileExceptionDialogVisible = true
            }
            ChatSideEffect.FileNotAllowedException -> {
                fileExceptionTitleId = R.string.not_allowed_file
                fileExceptionContentId = R.string.not_allowed_file_comment
                fileExceptionDialogVisible = true
            }
            is ChatSideEffect.FileSizeException -> {
                fileExceptionTitleId = R.string.big_size_file
                fileExceptionContentId = R.string.big_size_file_comment
                fileExceptionOnBtnPressed =
                    {
                        vm.postFile(uri = effect.uri, context = context, approve = true)
                        fileExceptionDialogVisible = false
                    }
                fileExceptionDialogVisible = true
            }
            ChatSideEffect.FinishRoom -> {
                state.chatSocket.close()
                navController.popBackStack()
            }
            is ChatSideEffect.ReceiveChat -> {
                MainScope().launch {
                    chatListState.scrollToItem(effect.chatSize)
                }
            }
            is ChatSideEffect.ReceiveChatUpdate -> {
                vm.editChatList(effect.chat)
            }
            is ChatSideEffect.ReceiveChatDelete -> {
                vm.deleteChatList(effect.chatId, deleteMsg)
            }
            is ChatSideEffect.SuccessUpload -> {
                state.chatSocket.send(roomId = roomId, text = effect.url)
            }
        }
    }

    Column(modifier = Modifier.pointerInput(Unit) {
        detectTapGestures {
            selectItemUUID = null
            focusManager.clearFocus()
        }
    }) {
        MiHeader(
            modifier = Modifier.background(Color(0xFFF2F2F2)),
            backPressed = { exitChatDialogVisible = true })
        Box(modifier = Modifier.weight(1f)) {
            ChatList(
                chatList = state.chatList,
                uploadList = state.uploadList,
                chatListState = chatListState,
                selectItemUUID = selectItemUUID,
                longClickAction = { uuid, time ->
                    selectItemUUID = if (time.plusMinutes(1) >= LocalTime.now()) {
                        uuid
                    } else {
                        null
                    }
                },
                editAction = { id, msg ->
                    text = msg
                    editMsgId = id
                    inputFocusRequester.requestFocus()
                    selectItemUUID = null
                },
                deleteAction = {
                    state.chatSocket.send(roomId = roomId, messageId = it, messageType = "DELETE")
                    selectItemUUID = null
                })
        }
        ChatInput(text = text, onValueChange = {
            text = it
        }, focusRequester = inputFocusRequester,
            sendAction = {
                emptyTime = EmptyTime
                if (editMsgId != null) {
                    state.chatSocket.send(
                        roomId = roomId,
                        messageId = editMsgId,
                        text = it,
                        messageType = "UPDATE"
                    )
                    editMsgId = null
                } else {
                    state.chatSocket.send(roomId = roomId, text = it)
                }
            }, fileSendAction = {
                vm.postFile(uri = it, context = context)
            }, isEditable = (editMsgId != null)
        )
        Spacer(modifier = Modifier.height(18.dp))
        BasicDialog(
            visible = exitChatDialogVisible,
            title = stringResource(id = R.string.main_screen),
            content = stringResource(id = R.string.main_screen_comment),
            onDismissRequest = { exitChatDialogVisible = false },
            onBtnPressed = {
                state.chatSocket.close()
                navController.popBackStack()
            })
        EmptyDialog(visible = emptyDialogVisible, onDismissRequest = {
            emptyDialogVisible = false
            emptyTime = EmptyTime
        }, onTimeOut = {
            emptyDialogVisible = false
            navController.popBackStack()
        })
        BasicDialog(
            visible = fileExceptionDialogVisible,
            title = stringResource(id = fileExceptionTitleId),
            content = stringResource(id = fileExceptionContentId),
            onDismissRequest = { fileExceptionDialogVisible = false },
            onBtnPressed = fileExceptionOnBtnPressed
        )
    }
}

@Composable
fun ChatList(
    chatList: List<ChatData>,
    uploadList: List<Uri>,
    chatListState: LazyListState = rememberLazyListState(),
    selectItemUUID: String?,
    longClickAction: (String?, LocalTime) -> Unit,
    editAction: (String, String) -> Unit,
    deleteAction: (String) -> Unit,
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
        items(chatList) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp),
                horizontalArrangement = if (item.isMe) Arrangement.End else Arrangement.Start
            ) {
                if (item.isMe) {
                    ClientChat(
                        item = item,
                        longClickAction = longClickAction,
                        isFile = item.text.contains("https://mitalk-s3.s3.ap-northeast-2.amazonaws.com/"),
                        itemVisible = selectItemUUID == item.id,
                        editAction = editAction,
                        deleteAction = {
                            deleteAction(it)
                        }
                    )
                } else {
                    CounselorChat(item = item)
                }
            }
        }
        items(uploadList) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .background(color = MitalkColor.White, shape = ClientChatShape),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MitalkColor.MainBlue)
                }
            }
        }
        items(1) {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ChatInput(
    text: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    sendAction: (String) -> Unit,
    fileSendAction: (Uri) -> Unit,
    isEditable: Boolean,
) {
    var isExpand by remember { mutableStateOf(false) }
    var targetValue by remember { mutableStateOf(0F) }
    val launcherFile = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            fileSendAction(it)
        }
    }
    val rotateValue: Float by animateFloatAsState(
        targetValue = targetValue,
        tween(300)
    )
    Column {
        if (isEditable) {
            Regular14NO(
                text = "문자를 수정 중 입니다.",
                modifier = Modifier.background(
                    color = Color(0xFFF3F3F3),
                    shape = RoundedCornerShape(5.dp)
                )
            )
        }
        Row(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(7.dp))
            IconButton(icon = MitalkIcon.Plus, modifier = Modifier.rotate(rotateValue), onClick = {
                isExpand = !isExpand
                targetValue = if (isExpand) {
                    45F
                } else {
                    0F
                }
            })
            if (isExpand) {
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(icon = MitalkIcon.Picture, onClick = {
                    launcherFile.launch("image/*")
                })
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(icon = MitalkIcon.Video, onClick = {
                    launcherFile.launch("video/*")
                })
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(icon = MitalkIcon.Document, onClick = {
                    launcherFile.launch("application/*")
                })
            }
            Spacer(modifier = Modifier.width(5.dp))
            ChatEditText(
                focusRequester = focusRequester,
                value = text,
                modifier = Modifier.weight(1f),
                onValueChange = onValueChange
            )
            IconButton(icon = MitalkIcon.Send, onClick = {
                if (text.isNotBlank()) {
                    sendAction(text)
                }
                onValueChange("")
            })
        }
    }
}

@Composable
fun ChatEditText(
    focusRequester: FocusRequester,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxHeight()
            .background(color = MitalkColor.White, shape = RoundedCornerShape(13.dp))
            .border(width = 1.dp, color = Color(0xD2D2D2), shape = RoundedCornerShape(13.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .focusRequester(focusRequester = focusRequester),
            onValueChange = { onValueChange(it) },
            textStyle = MiTalkTypography.regular14NO,
            decorationBox = {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.focusRequester(focusRequester = focusRequester)
                ) {
                    it()
                }
            }
        )
    }
}

@Composable
fun CounselorChat(
    item: ChatData,
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
            ChatItem(
                item = item.text, isMe = item.isMe, modifier = Modifier
                    .background(
                        color = MitalkColor.MainBlue,
                        shape = CounselorChatShape
                    )
                    .widthIn(min = 0.dp, max = 180.dp)
                    .padding(horizontal = 7.dp, vertical = 5.dp)
            )
        }
        Spacer(modifier = Modifier.width(3.dp))
        Light09NO(text = item.time.toChatTime())
    }
}

@Composable
fun ClientChat(
    item: ChatData,
    isFile: Boolean,
    longClickAction: (String?, LocalTime) -> Unit,
    editAction: (String, String) -> Unit,
    deleteAction: (String) -> Unit,
    itemVisible: Boolean,
) {
    Box {
        if (itemVisible) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(y = (-25).dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .width(55.dp)
                            .height(17.dp)
                            .background(
                                color = Color(0xFFF3F3F3),
                                shape = RoundedCornerShape(5.dp)
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!isFile) {
                            Light09NO(
                                text = stringResource(id = R.string.update),
                                color = Color(0xFF4200FF),
                                modifier = Modifier
                                    .padding(end = 3.dp)
                                    .miClickable {
                                        editAction(item.id, item.text)
                                    })
                            Spacer(
                                modifier = Modifier
                                    .background(Color(0x66C9C6C6))
                                    .width((0.5).dp)
                                    .fillMaxHeight(0.7f)
                            )
                        }
                        Light09NO(
                            text = stringResource(id = R.string.delete),
                            color = Color(0xFFFF0000),
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .miClickable {
                                    deleteAction(item.id)
                                })
                    }
                    Box(
                        modifier = Modifier
                            .width(10.dp)
                            .height(5.dp)
                            .background(color = Color(0xFFF3F3F3), shape = TriangleShape())
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Light09NO(text = item.time.toChatTime())
            Spacer(modifier = Modifier.width(3.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = MitalkColor.White,
                        shape = ClientChatShape
                    )
                    .widthIn(min = 0.dp, max = 200.dp)
                    .padding(horizontal = 7.dp, vertical = 5.dp)
            ) {
                ChatItem(
                    item.text,
                    modifier = Modifier.miClickable(rippleEnabled = false, onLongClick = {
                        longClickAction(item.id, item.time)
                    }) { longClickAction(null, item.time) })
            }
        }
    }
}

@Composable
fun ChatItem(
    item: String,
    isMe: Boolean = true,
    modifier: Modifier = Modifier,
    findText: String = ""
) {
    val context = LocalContext.current
    if (item.contains("https://mitalk-s3.s3.ap-northeast-2.amazonaws.com/")) {
        val fileExt = item.split(".").last().lowercase()
        if (ImageAllowedList.contains(fileExt)) {
            AsyncImage(model = item, contentDescription = "Chat Image", modifier = modifier)
        } else if (VideoAllowedList.contains(fileExt)) {
            VideoPlayer(url = item, modifier = modifier)
        } else if (DocumentAllowedList.contains(fileExt)) {
            Bold11NO(text = "File Download", modifier = Modifier.clickable {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item)))
            })
        }
    } else {
        Bold11NO(
            text = item,
            modifier = modifier.background(if (findText.isNotEmpty() && item.contains(findText)) Color.Yellow else Color.Transparent),
            color = if (isMe) MitalkColor.Black else MitalkColor.White
        )
    }
}

@Composable
fun IconButton(
    icon: MitalkIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier
        .width(20.dp)
        .miClickable(rippleEnabled = false) {
            onClick()
        }) {
        Icon(
            painter = painterResource(id = icon.drawableId),
            contentDescription = icon.contentDescription,
            tint = MitalkColor.MainBlue,
            modifier = modifier
                .fillMaxHeight()
        )
    }
}

@Composable
@Preview
fun showChatRoomScreen() {
    MitalkTheme() {
        val navController = rememberNavController()
        ChatRoomScreen(navController, "")
    }
}