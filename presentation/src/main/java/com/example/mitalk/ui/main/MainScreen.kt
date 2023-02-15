package com.example.mitalk.ui.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.param.ReviewParam
import com.example.mitalk.AppNavigationItem
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.miClickable
import com.example.mitalk.R
import com.example.mitalk.mvi.MainSideEffect
import com.example.mitalk.mvi.MainState
import com.example.mitalk.ui.chat.ExitChatDialog
import com.example.mitalk.util.observeWithLifecycle
import com.example.mitalk.util.theme.*
import com.example.mitalk.vm.main.MainViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
) {

    val container = mainViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(Unit) {
        mainViewModel.checkReviewState()
    }

    sideEffect.observeWithLifecycle {
        when (it) {
            MainSideEffect.Logout -> {
                navController.navigate(
                    route = AppNavigationItem.Splash.route
                ) {
                    popUpTo(0)
                }
            }
            MainSideEffect.ReviewSuccess -> {
                mainViewModel.clearCounsellorId()
            }
        }
    }

    val newAnswer = true

    val callCheck = false
    val counselorBackground = if (callCheck) Color(0xFFD49393) else MitalkColor.MainGreen
    val counselorText =
        if (callCheck) stringResource(id = R.string.counselor_connect_again)
        else stringResource(id = R.string.counselor_connect)
    val counselorComment =
        if (callCheck) stringResource(id = R.string.counselor_connect_again_comment)
        else stringResource(id = R.string.counselor_connect_comment)

    var exitDialogVisible by remember { mutableStateOf(false) }

    val scroller = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(state = scroller)
    ) {
        MiHeader(
            backBtn = false,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = MitalkIcon.Logo.drawableId),
                    contentDescription = MitalkIcon.Google_Icon.contentDescription,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))

                Bold20NO(
                    text = "마이톡",
                    color = Color(0xFF6C7FE9),
                )
            }
        }

        Spacer(modifier = Modifier.height(26.93.dp))

        if (newAnswer) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = MitalkIcon.Bulb_Img.drawableId),
                    contentDescription = MitalkIcon.Bulb_Img.contentDescription,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(2.dp))

                Bold11NO(
                    text = stringResource(id = R.string.new_counselor_answer),
                    color = Color(0xFFD49393)
                )
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        MainContent(
            text = counselorText,
            comment = counselorComment,
            backgroundColor = counselorBackground,
            icon = painterResource(id = MitalkIcon.Counselor_Img.drawableId),
            callCheck = callCheck,
        ) {
            navController.navigate(
                route = AppNavigationItem.ChatType.route
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        MainContent(
            text = stringResource(id = R.string.record_open),
            comment = stringResource(id = R.string.record_open_comment),
            backgroundColor = MitalkColor.MainBlue,
            icon = painterResource(id = MitalkIcon.Record_Img.drawableId)
        ) {
            navController.navigate(
                route = AppNavigationItem.Record.route
            )
        }

        EvaluationDialog(
            name = "백승민",
            visible = (state.counsellorId != null),
            onDismissRequest = {
                mainViewModel.postReview(ReviewParam(null,null, listOf(), null))
            },
            onBtnPressed = {}
        )

        ExitChatDialog(
            visible = exitDialogVisible,
            title = stringResource(id = R.string.logout),
            content = stringResource(id = R.string.logout_real),
            onDismissRequest = { exitDialogVisible = false }
        ) {
            mainViewModel.logout()
        }

        Spacer(modifier = Modifier.height(12.dp))

        MainContent(
            text = stringResource(id = R.string.question_many),
            comment = stringResource(id = R.string.question_many_comment),
            backgroundColor = MitalkColor.MainBrown,
            icon = painterResource(id = MitalkIcon.Question_Img.drawableId)
        ) {
            navController.navigate(
                route = AppNavigationItem.Question.route
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        MainContent(
            text = stringResource(id = R.string.setting),
            comment = "",
            backgroundColor = Color(0xFF646464),
            icon = painterResource(id = MitalkIcon.Setting_Img.drawableId)
        ) {

        }

        Spacer(modifier = Modifier.height(12.dp))

        MainContent(
            text = stringResource(id = R.string.logout),
            comment = "",
            backgroundColor = Color(0xFF58EBD0),
            icon = painterResource(id = MitalkIcon.Logout_Img.drawableId)
        ) {
            exitDialogVisible = true
        }
    }
}

@Stable
private val ContentShape = RoundedCornerShape(10.dp)

@Composable
private fun MainContent(
    text: String,
    comment: String,
    backgroundColor: Color,
    icon: Painter,
    callCheck: Boolean = false,
    onPressed: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(
                color = backgroundColor,
                shape = ContentShape,
            )
            .clip(shape = ContentShape)
            .miClickable { onPressed() }
    ) {

        Spacer(modifier = Modifier.width(17.dp))

        Column {
            if(comment.isEmpty()) {
                Bold26NO(
                    text = text,
                    color = MitalkColor.White,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            } else {
                Spacer(modifier = Modifier.height(20.dp))

                if (callCheck) {
                    Row {
                        Bold20NO(
                            text = text,
                            color = MitalkColor.White,
                        )
                        Bold20NO(
                            text = stringResource(id = R.string.call_out),
                            color = Color(0xFFF1EBB4),
                        )
                    }
                } else {
                    Bold20NO(
                        text = text,
                        color = MitalkColor.White,
                    )
                }

                Spacer(modifier = Modifier.height(7.dp))

                Regular12NO(
                    text = comment,
                    color = MitalkColor.White,
                )
            }
        }

        Image(
            painter = icon,
            contentDescription = "main content icon",
            modifier = Modifier
                .padding(end = 13.dp)
                .fillMaxSize()
                .wrapContentWidth(align = Alignment.End)
                .wrapContentHeight(align = Alignment.CenterVertically)
                .size(60.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShowMainScreen() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}