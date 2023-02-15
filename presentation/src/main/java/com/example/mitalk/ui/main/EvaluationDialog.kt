package com.example.mitalk.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.domain.param.ReviewParam
import com.example.mitalk.R
import com.example.mitalk.ui.util.EvaluateItemType
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.*
import com.example.mitalk.vm.main.MainViewModel

@Composable
fun EvaluationDialog(
    name: String,
    mainViewModel: MainViewModel,
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onBtnPressed: () -> Unit,
) {

    val container = mainViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

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
                DialogStar(starCount = state.starCount, onStarPressed = { mainViewModel.inputStarCount(it) })
                Spacer(modifier = Modifier.height(8.dp))
                DialogWhatLike(starCount = state.starCount)
                Spacer(modifier = Modifier.height(9.dp))
                EvaluateList(
                    starCount = state.starCount,
                    onPressed = {
                        if ((5 - state.starCount) < 2) {
                            when (it.type) {
                                state.badEvaluationSelected1?.type -> {
                                    mainViewModel.inputBadEvaluationSelected1(null)
                                }
                                state.badEvaluationSelected2?.type -> {
                                    val swap = state.badEvaluationSelected1
                                    mainViewModel.inputBadEvaluationSelected1(null)
                                    mainViewModel.inputBadEvaluationSelected2(swap)
                                }
                                else -> {
                                    val swap = state.badEvaluationSelected2
                                    mainViewModel.inputBadEvaluationSelected2(it)
                                    mainViewModel.inputBadEvaluationSelected1(swap)
                                }
                            }
                        } else {
                            when (it.type) {
                                state.goodEvaluationSelected1?.type -> {
                                    mainViewModel.inputGoodEvaluationSelected1(null)
                                }
                                state.goodEvaluationSelected2?.type -> {
                                    val swap = state.goodEvaluationSelected1
                                    mainViewModel.inputGoodEvaluationSelected1(null)
                                    mainViewModel.inputGoodEvaluationSelected2(swap)
                                }
                                else -> {
                                    val swap = state.goodEvaluationSelected2
                                    mainViewModel.inputGoodEvaluationSelected2(it)
                                    mainViewModel.inputGoodEvaluationSelected1(swap)
                                }
                            }
                        }
                    },
                    evaluationAnswerList =
                    if ((5 - state.starCount) < 2) listOf(
                        state.badEvaluationSelected1?.type,
                        state.badEvaluationSelected2?.type
                    )
                    else listOf(state.goodEvaluationSelected1?.type, state.goodEvaluationSelected2?.type)
                )
                Spacer(modifier = Modifier.height(36.dp))
                DialogEditText(
                    value = state.evaluateComment,
                    onValueChanged = { mainViewModel.inputEvaluateComment(it) }
                )
                Spacer(modifier = Modifier.height(5.dp))
                DialogBtn { onBtnPressed() }
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
        items(listOf(5, 4, 3, 2, 1)) {
            if (starCount <= it) {
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
    val text =
        if ((5 - starCount) < 2) stringResource(id = R.string.what_bad_counselor)
        else stringResource(id = R.string.what_like_counselor)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Bold08NO(text = text)
        Regular06NO(text = "(" + stringResource(id = R.string.can_choose_from0_to2) + ")")
    }
}

@Composable
fun EvaluateList(
    starCount: Int,
    onPressed: (EvaluateItemType) -> Unit,
    evaluationAnswerList: List<String?>,
) {
    val list1 =
        if ((5 - starCount) < 2) listOf(
            EvaluateItemType.UNKINDNESS,
            EvaluateItemType.USELESS,
            EvaluateItemType.NOT_APPROPRIATE_ANSWER,
        ) else listOf(
            EvaluateItemType.KINDNESS,
            EvaluateItemType.USEFUL,
            EvaluateItemType.LISTEN
        )
    val list2 =
        if ((5 - starCount < 2)) listOf(
            EvaluateItemType.DIFFICULT_EXPLANATION,
            EvaluateItemType.SLANG,
            EvaluateItemType.SLOW_ANSWER
        ) else listOf(
            EvaluateItemType.EXPLANATION,
            EvaluateItemType.COMFORT,
            EvaluateItemType.FASTANSWER
        )

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .wrapContentWidth(Alignment.End),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.End,
        ) {
            items(list1) {
                EvaluateItem(
                    item = it,
                    onPressed = onPressed,
                    evaluationAnswerList = evaluationAnswerList,
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            items(list2) {
                EvaluateItem(
                    item = it,
                    onPressed = onPressed,
                    evaluationAnswerList = evaluationAnswerList,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
        }
    }
}

@Stable
private val EvaluateItemShape = RoundedCornerShape(5.dp)

@Composable
fun EvaluateItem(
    item: EvaluateItemType,
    modifier: Modifier = Modifier,
    onPressed: (EvaluateItemType) -> Unit,
    evaluationAnswerList: List<String?>
) {
    val containState = evaluationAnswerList.contains(item.type)
    val backgroundColor =
        if (containState) Color(0xFFBEBEBE)
        else Color(0xFFE9EBE9)

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = EvaluateItemShape,
            )
            .clip(shape = EvaluateItemShape)
            .miClickable(rippleEnabled = false) {
                onPressed(item)
            },
        contentAlignment = Alignment.Center,
    ) {
        Medium10NO(
            text = stringResource(id = item.typeId),
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(vertical = 6.dp)
        )
    }
}

@Composable
fun DialogEditText(
    value: String,
    onValueChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .fillMaxWidth()
            .height(25.dp)
            .background(
                color = Color(0xFFE9E9E9),
                shape = RoundedCornerShape(5.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            value = value,
            onValueChange = onValueChanged,
            textStyle = MiTalkTypography.regular7No,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default,
            ),
            decorationBox = @Composable {
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    it()

                    if (value.isEmpty()) {
                        Regular7NO(text = stringResource(id = R.string.evaluation_comment_hint))
                    }
                }
            }
        )
    }
}

@Stable
private val BtnShape = RoundedCornerShape(2.dp)

@Composable
fun DialogBtn(
    onBtnPressed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .width(125.dp)
            .height(25.dp)
            .background(
                color = Color(0xFFFFDC64),
                shape = BtnShape
            )
            .clip(shape = BtnShape)
            .miClickable {
                onBtnPressed()
            },
        contentAlignment = Alignment.Center,
    ) {
        Bold08NO(text = stringResource(id = R.string.submit))
    }
}
