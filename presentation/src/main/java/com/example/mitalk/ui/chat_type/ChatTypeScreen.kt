package com.example.mitalk.ui.chat_type

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.R
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.theme.MitalkColor
import com.example.mitalk.util.theme.Regular14NO
import com.example.mitalk.util.theme.Regular7NO

@Stable
private val ChatTypeBoxShape = RoundedCornerShape(8.dp)

@Composable
fun ChatTypeScreen(
    navController: NavController,
) {
    Column {
        MiHeader(
            navController = navController,
            text = stringResource(id = R.string.consulting_connect)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.weight(1f)) {
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                ChatTypeBox(
                    title = stringResource(id = R.string.function_suggest),
                    comment = stringResource(id = R.string.function_suggest_comment),
                    img = MitalkIcon.Function_Suggest_Img,
                    modifier = Modifier
                        .weight(7f)
                        .background(color = Color(0xFF62A3A7), shape = ChatTypeBoxShape),
                    imgModifier = Modifier.padding(bottom = 16.dp, start = 12.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                ChatTypeBox(
                    title = stringResource(id = R.string.function_question),
                    comment = stringResource(id = R.string.function_suggest_comment),
                    img = MitalkIcon.Function_Question_Img,
                    modifier = Modifier
                        .weight(5f)
                        .background(color = Color(0xFF81A578), shape = ChatTypeBoxShape),
                    imgModifier = Modifier
                )
                Spacer(modifier = Modifier.weight(1f))
                ChatTypeBox(
                    title = stringResource(id = R.string.alliance_inquiry),
                    comment = stringResource(id = R.string.alliance_inquiry_comment),
                    img = MitalkIcon.Alliance_Inquiry_Img,
                    modifier = Modifier
                        .weight(6f)
                        .background(color = Color(0xFF959FC3), shape = ChatTypeBoxShape),
                    imgModifier = Modifier
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                ChatTypeBox(
                    title = stringResource(id = R.string.bug_report),
                    comment = stringResource(id = R.string.bug_report_comment),
                    img = MitalkIcon.Bug_Report_Img,
                    modifier = Modifier
                        .weight(5f)
                        .background(color = Color(0xFFA96A6A), shape = ChatTypeBoxShape),
                    imgModifier = Modifier.padding(top = 12.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                ChatTypeBox(
                    title = stringResource(id = R.string.product_feedback),
                    comment = stringResource(id = R.string.product_feedback_comment),
                    img = MitalkIcon.Product_Feedback_Img,
                    modifier = Modifier
                        .weight(7f)
                        .background(color = Color(0xFFB49C79), shape = ChatTypeBoxShape),
                    imgModifier = Modifier.padding(start = 12.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                ChatTypeBox(
                    title = stringResource(id = R.string.etc),
                    img = MitalkIcon.Etc_Img,
                    modifier = Modifier
                        .weight(6f)
                        .background(color = Color(0xFF698EAF), shape = ChatTypeBoxShape),
                    imgModifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 5.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun ChatTypeBox(
    title: String,
    comment: String? = null,
    img: MitalkIcon,
    modifier: Modifier,
    imgModifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .miClickable {

            }
    ) {
        Column(
            modifier = Modifier.padding(top = 15.dp, start = 12.dp)
        ) {
            Regular14NO(
                text = title,
                color = MitalkColor.White,
                modifier = Modifier.wrapContentHeight()
            )
            if (comment != null) {
                Regular7NO(
                    text = comment,
                    color = MitalkColor.White,
                    modifier = Modifier.wrapContentHeight()
                )
            }
        }
        Image(
            painter = painterResource(id = img.drawableId),
            contentDescription = img.contentDescription,
            modifier = imgModifier.align(alignment = Alignment.BottomEnd),
        )
    }
}

@Composable
@Preview
fun showChatTypeScreen() {
    val navController = rememberNavController()
    ChatTypeScreen(navController = navController)
}