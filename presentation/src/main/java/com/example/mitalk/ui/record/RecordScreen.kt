package com.example.mitalk.ui.record

import android.icu.text.AlphabeticIndex.Record
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.AppNavigationItem
import com.example.mitalk.DeepLinkKey
import com.example.mitalk.R
import com.example.mitalk.mvi.RecordState
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.ui.util.RecordItemType
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.*
import com.example.mitalk.util.toRecordDate
import com.example.mitalk.vm.record.RecordViewModel

@Composable
fun RecordScreen(
    navController: NavController,
    vm: RecordViewModel = hiltViewModel(),
) {

    val container = vm.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(Unit) {
        vm.getRecordList()
    }

    Column {
        MiHeader(
            backPressed = { navController.popBackStack() },
            text = stringResource(id = R.string.consulting_record),
        )
        RecordList(
            navController = navController,
            list = state.recordList
        )
    }
}

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
    } catch (e: StringIndexOutOfBoundsException) {
        0
    }

private fun returnLastDate(list: List<RecordState.RecordData>, index: Int): Int =
    if (index == 0) 0
    else dateToInt(list[index - 1].time)

@Composable
private fun RecordList(
    navController: NavController,
    list: List<RecordState.RecordData>,
) {
    LazyColumn {
        itemsIndexed(list) { index, it ->
            val type = when (it.type) {
                RecordItemType.QUESTION.type -> RecordItemType.QUESTION
                RecordItemType.FEEDBACK.type -> RecordItemType.FEEDBACK
                RecordItemType.BUG.type -> RecordItemType.BUG
                RecordItemType.SUGGEST.type -> RecordItemType.SUGGEST
                RecordItemType.INQUIRY.type -> RecordItemType.INQUIRY
                RecordItemType.ETC.type -> RecordItemType.ETC
                else -> RecordItemType.ElSE
            }
            RecordItem(
                date = it.time,
                icon = painterResource(id = returnIcon(index)),
                type = type,
                counselor = it.counsellorName,
                lastDate = returnLastDate(list, index),
            ) {
                navController.navigate(
                    route = AppNavigationItem.RecordDetail.route
                            + DeepLinkKey.HEADER_ID + type.titleId
                            + DeepLinkKey.RECORD_ID + it.recordId
                            + DeepLinkKey.COUNSELLOR_NAME + it.counsellorName
                )
            }
        }
    }
}

@Composable
private fun RecordItem(
    date: String,
    icon: Painter,
    type: RecordItemType,
    counselor: String,
    lastDate: Int,
    onClicked: () -> Unit,
) {
    val thisDateInt = dateToInt(date)
    val dividerHeight =
        if (lastDate - thisDateInt > 1000) 100.dp
        else if (lastDate - thisDateInt > 100) 50.dp
        else if (lastDate > thisDateInt) 25.dp
        else 10.dp
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
                text = date.toRecordDate(),
                modifier = Modifier.width(83.dp)
            )

            Icon(
                painter = icon,
                contentDescription = "record item",
                modifier = Modifier.size(11.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Medium15GM(
                text = stringResource(id = type.titleId),
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
