package com.example.mitalk.ui.util

import androidx.compose.ui.graphics.Color
import com.example.mitalk.R
import com.example.mitalk.util.theme.MitalkColor

enum class RecordItemType(
    val type: String,
    val textColor: Color,
    val titleId: Int
) {
    QUESTION(
        type = "FEATURE_QUESTION",
        textColor = Color(0xFFD49393),
        titleId = R.string.function_question
    ),
    FEEDBACK(
        type = "FEEDBACK",
        textColor = MitalkColor.MainBlue,
        titleId = R.string.product_feedback
    ),
    BUG(
        type = "BUG",
        textColor = MitalkColor.MainGreen,
        titleId = R.string.bug_report
    ),
    SUGGEST(
        type = "FEATURE_PROPOSAL",
        textColor = MitalkColor.MainBrown,
        titleId = R.string.function_suggest
    ),
    INQUIRY(
        type = "PURCHASE",
        textColor = Color(0xFFA1A0DF),
        titleId = R.string.alliance_inquiry
    ),
    ETC(
        type = "ETC",
        textColor = Color(0xFFC294C2),
        titleId = R.string.etc
    ),
    ElSE(
        type = "",
        textColor = MitalkColor.Black,
        titleId = R.string.function_question
    ),
}