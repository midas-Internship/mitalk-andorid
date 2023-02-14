package com.example.mitalk.ui.record

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.R
import com.example.mitalk.ui.util.MiHeader

@Composable
fun RecordScreen(
    navController: NavController,
) {
    MiHeader(
        navController = navController,
        text = stringResource(id = R.string.consulting_record)
    )
}

@Composable
@Preview(showBackground = true)
fun ShowRecordScreen() {
    val navController = rememberNavController()
    RecordScreen(navController = navController)
}
