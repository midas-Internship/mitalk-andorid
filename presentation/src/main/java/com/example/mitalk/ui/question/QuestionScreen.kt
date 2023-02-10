package com.example.mitalk.ui.question

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
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
fun QuestionScreen(
    navController: NavController,
) {
    Column {
        MiHeader(
            navController = navController,
            text = stringResource(id = R.string.question_many)
        )
        
        Spacer(modifier = Modifier.height(19.dp))

        QuestionContent(question = "질문", answer = "답변")
    }
}

@Stable
private val ItemShape = RoundedCornerShape(10.dp)

@Composable
private fun QuestionContent(
    question: String,
    answer: String,
) {

    var open by remember { mutableStateOf(false) }
    var targetValue by remember { mutableStateOf(180F) }
    val rotateValue: Float by animateFloatAsState(
        targetValue = targetValue,
        tween(500)
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(
                color = MitalkColor.White,
                shape = ItemShape,
            )
            .border(
                width = 1.dp,
                color = Color(0xFFEBEBEB),
                shape = ItemShape,
            )
            .clip(shape = ItemShape)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .miClickable {
                    targetValue = if (!open) {
                        270F
                    } else {
                        180F
                    }
                    open = !open
                }
                .padding(vertical = 10.dp)
        ) {
            Spacer(modifier = Modifier.width(10.dp))

            Bold13NO(
                text = "Q",
                color = Color(0xFF4BE8DE),
            )

            Spacer(modifier = Modifier.width(6.dp))
            
            Medium13NO(text = question)

           SeeAnswerIcon(rotateValue = rotateValue)
        }

        if (open) {
            Row {
                Spacer(modifier = Modifier.width(10.dp))

                Bold13NO(
                    text = "A",
                    color = Color(0xFFEB5656),
                )

                Spacer(modifier = Modifier.width(6.dp))

                Light09NO(
                    text = answer,
                    modifier = Modifier.padding(end = 21.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun SeeAnswerIcon(
    rotateValue: Float,
) {
    Icon(
        painter = painterResource(id = MitalkIcon.Back.drawableId),
        contentDescription = MitalkIcon.Back.contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.End)
            .padding(end = 21.dp)
            .rotate(rotateValue)
    )
}

@Composable
@Preview(showBackground = true)
fun ShowQuestionScreen() {
    val navController = rememberNavController()

    QuestionScreen(navController = navController)
}
