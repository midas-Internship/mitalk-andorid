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

enum class EvaluateItemType(
    val typeId: Int,
    val type: String
) {
    KINDNESS(
        typeId = R.string.is_kind,
        type = "KINDNESS"
    ),
    USEFUL(
        typeId = R.string.is_useful,
        type = "USEFUL"
    ),
    LISTEN(
        typeId = R.string.listen_well,
        type = "LISTEN"
    ),
    EXPLANATION(
        typeId = R.string.is_good_explanation,
        type = "EXPLANATION"
    ),
    COMFORT(
        typeId = R.string.is_comfortable,
        type = "COMFORT"
    ),
    FASTANSWER(
        typeId = R.string.is_fast_reply,
        type = "FAST_ANSWER"
    ),
    UNKINDNESS(
        typeId = R.string.is_unkind,
        type = "UNKINDNESS"
    ),
    USELESS(
        typeId = R.string.is_not_useful,
        type = "USELESS"
    ),
    NOT_APPROPRIATE_ANSWER(
        typeId = R.string.inappropriate_answer,
        type = "NOT_APPROPRIATE_ANSWER"
    ),
    DIFFICULT_EXPLANATION(
        typeId = R.string.is_bad_explanation,
        type = "DIFFICULT_EXPLANATION"
    ),
    SLANG(
        typeId = R.string.abuse_slang,
        type = "SLANG"
    ),
    SLOW_ANSWER(
        typeId = R.string.is_slow_reply,
        type = "SLOW_ANSWER"
    )
}