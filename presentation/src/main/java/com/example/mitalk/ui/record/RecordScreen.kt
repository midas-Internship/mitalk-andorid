package com.example.mitalk.ui.record

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
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

    val recordList = listOf(
        RecordData("2023.04.14", stringResource(id = R.string.function_question), "백승민"),
        RecordData("2023.02.17", stringResource(id = R.string.product_feedback), "백승민"),
        RecordData("2023.02.16", stringResource(id = R.string.bug_report), "백승민"),
        RecordData("2023.02.15", stringResource(id = R.string.function_suggest), "백승민"),
        RecordData("2023.02.14", stringResource(id = R.string.alliance_inquiry), "백승민"),
        RecordData("2023.02.14", stringResource(id = R.string.etc), "백승민"),
        RecordData("2021.02.14", stringResource(id = R.string.consulting_record), "백승민"),
    )

    Column {
        MiHeader(
            backPressed = { navController.popBackStack() },
            text = stringResource(id = R.string.consulting_record),
        )
        RecordList(list = recordList)
    }
}

data class RecordData(
    val date: String,
    val title: String,
    val counselor: String,
)

private fun returnIcon(index: Int): Int =
    when (index % 3) {
        0 -> MitalkIcon.Fill_Circle_Icon.drawableId
        1 -> MitalkIcon.Empty_Circle_Icon.drawableId
        2 -> MitalkIcon.Two_Circle_Icon.drawableId
        else -> MitalkIcon.Empty_Circle_Icon.drawableId
    }

private fun dateToInt(date: String): Int =
    try {
        val year = date.substring(0..3)
        val month = date.substring(5..6)
        val day = date.substring(8..9)

        (year + month + day).toInt()
    } catch (e: StringIndexOutOfBoundsException) { 0 }
private fun returnLastDate(list: List<RecordData>, index: Int): Int =
    if (index == 0) 0
    else dateToInt(list[index-1].date)

@Composable
private fun RecordList(
    list: List<RecordData>,
) {
    LazyColumn {
        itemsIndexed(list) {index, it ->
            RecordItem(
                date = it.date,
                icon = painterResource(id = returnIcon(index)),
                title = it.title,
                counselor = it.counselor,
                lastDate = returnLastDate(list, index),
            ) {

            }
        }
    }
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
private fun RecordItem(
    date: String,
    icon: Painter,
    title: String,
    counselor: String,
    lastDate: Int,
    onClicked: () -> Unit
) {
    val thisDateInt = dateToInt(date)
    val dividerHeight =
        if (lastDate - thisDateInt > 1000) 100.dp
        else if(lastDate - thisDateInt > 100) 50.dp
        else if (lastDate > thisDateInt) 25.dp
        else 10.dp
    val type = when (title) {
        stringResource(id = R.string.function_question) -> RecordItemType.QUESTION
        stringResource(id = R.string.product_feedback) -> RecordItemType.FEEDBACK
        stringResource(id = R.string.bug_report) -> RecordItemType.BUG
        stringResource(id = R.string.function_suggest) -> RecordItemType.SUGGEST
        stringResource(id = R.string.alliance_inquiry) -> RecordItemType.INQUIRY
        stringResource(id = R.string.etc) -> RecordItemType.ETC
        else -> RecordItemType.ElSE
    }
    Column {
        Divider(
            color = MitalkColor.Black,
            modifier = Modifier
                .padding(start = 118.dp)
                .width(1.dp)
                .height(dividerHeight)
        )
        Row(
            modifier = Modifier
                .padding(vertical = 1.dp)
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

}

@Composable
@Preview(showBackground = true)
fun ShowRecordScreen() {
    val navController = rememberNavController()
    RecordScreen(navController = navController)
}
