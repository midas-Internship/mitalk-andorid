package com.example.mitalk.ui.record

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.R
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.*

@Composable
fun RecordScreen(
    navController: NavController,
) {
    MiHeader(
        navController = navController,
        text = stringResource(id = R.string.consulting_record)
    )
}

private enum class RecordItemType(
    val textColor: Color,
) {
    QUESTION(
        textColor = Color(0xFFD49393),
    ),
    FEEDBACK(
        textColor = MitalkColor.MainBlue,
    ),
    BUG(
        textColor = MitalkColor.MainGreen,
    ),
    SUGGEST(
        textColor = MitalkColor.MainBrown,
    ),
    INQUIRY(
        textColor = Color(0xFFA1A0DF),
    ),
    ETC(
        textColor = Color(0xFFC294C2),
    ),
    ElSE(
        textColor = MitalkColor.Black,
    ),
}

@Composable
fun RecordItem(
    date: String,
    icon: Painter,
    title: String,
    counselor: String,
    onClicked: () -> Unit
) {
    val type = when (title) {
        stringResource(id = R.string.function_question) -> RecordItemType.QUESTION
        stringResource(id = R.string.product_feedback) -> RecordItemType.FEEDBACK
        stringResource(id = R.string.bug_report) -> RecordItemType.BUG
        stringResource(id = R.string.function_suggest_comment) -> RecordItemType.SUGGEST
        stringResource(id = R.string.alliance_inquiry) -> RecordItemType.INQUIRY
        stringResource(id = R.string.etc) -> RecordItemType.ETC
        else -> RecordItemType.ElSE
    }
    Row(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .miClickable(rippleEnabled = false) { onClicked() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(30.dp))

        Medium10GM(
            text = date,
            modifier = Modifier.width(83.dp)
        )

        Icon(
            painter = icon,
            contentDescription = "record item",
            modifier = Modifier.size(11.dp)
        )
        
        Spacer(modifier = Modifier.width(10.dp))

        Medium15GM(
            text = title,
            color = type.textColor,
            modifier = Modifier.width(88.dp)
        )

        Medium10GM(text = counselor)

        Icon(
            painter = painterResource(id = MitalkIcon.Record_Icon.drawableId),
            contentDescription = MitalkIcon.Record_Icon.contentDescription,
            modifier = Modifier
                .padding(end = 30.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShowRecordScreen() {
//    val navController = rememberNavController()
//    RecordScreen(navController = navController)
    RecordItem(
        date = "2023.02.14",
        icon = painterResource(id = MitalkIcon.Two_Circle_Icon.drawableId),
        title = stringResource(id = R.string.product_feedback),
        counselor = "백승민",
        onClicked = {}
    )
}
