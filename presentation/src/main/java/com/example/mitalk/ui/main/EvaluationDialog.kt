package com.example.mitalk.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mitalk.util.theme.Bold13NO
import com.example.mitalk.util.theme.Light13NO
import com.example.mitalk.util.theme.MitalkColor
import com.example.mitalk.util.theme.MitalkIcon
import com.example.mitalk.R
import com.example.mitalk.util.miClickable

@Composable
fun EvaluationDialog(
    name: String,
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onBtnPressed: (Int, String, String) -> Unit,
) {
    var starCount by remember { mutableStateOf(5) }

    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest,
        ) {
            Column(
                modifier = Modifier
                    .width(270.dp)
                    .height(328.dp)
                    .background(
                        color = MitalkColor.White,
                        shape = RoundedCornerShape(12.dp),
                    )
            ) {
                Spacer(modifier = Modifier.height(18.dp))
                DialogClose(onDismissRequest = onDismissRequest)
                Spacer(modifier = Modifier.height(15.dp))
                DialogNameTag(name = name)
                Spacer(modifier = Modifier.height(8.dp))
                DialogStar(starCount = starCount, onStarPressed = { starCount = it })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun DialogClose(
    onDismissRequest: () -> Unit,
) {
    IconButton(
        onClick = {
            onDismissRequest()
        },
        modifier = Modifier
            .padding(end = 21.dp)
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.End)
            .size(9.dp)
    ) {
        Icon(
            painter = painterResource(id = MitalkIcon.Close.drawableId),
            contentDescription = MitalkIcon.Close.contentDescription,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
fun DialogNameTag(
    name: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally),
    ) {
        Bold13NO(text = "$name ")
        Light13NO(text = stringResource(id = R.string.how_was_counselor))
    }
}

@Composable
fun DialogStar(
    starCount: Int,
    onStarPressed: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally),
    ) {
        items(listOf(5,4,3,2,1)) {
            if(starCount <= it) {
                Image(
                    painter = painterResource(id = MitalkIcon.Star_On.drawableId),
                    contentDescription = MitalkIcon.Star_On.contentDescription,
                    modifier = Modifier
                        .miClickable(
                            rippleEnabled = false
                        ) {
                            onStarPressed(it)
                        }
                )
            } else {
                Image(
                    painter = painterResource(id = MitalkIcon.Star_Off.drawableId),
                    contentDescription = MitalkIcon.Star_On.contentDescription,
                    modifier = Modifier
                        .miClickable(
                            rippleEnabled = false
                        ) {
                            onStarPressed(it)
                        }
                )
            }
        }
    }
}

@Composable
fun DialogWhatLike(
    starCount: Int,
) {

}
