package com.example.mitalk.ui.record

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.R
import com.example.mitalk.ui.util.MiHeader
import com.example.mitalk.util.theme.MitalkColor
import com.example.mitalk.util.theme.MitalkIcon

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
    PROPOSAL(
        textColor = MitalkColor.MainBrown,
    ),
    INQUIRY(
        textColor = Color(0xFFA1A0DF)
    ),
    ETC(
        textColor = Color(0xFFC294C2)
    ),
}

@Composable
fun RecordItem(
    date: String,
    title: String,
    counselor: String,
    icon: Painter,
) {
    
}

@Composable
@Preview(showBackground = true)
fun ShowRecordScreen() {
    val navController = rememberNavController()
    RecordScreen(navController = navController)
}
