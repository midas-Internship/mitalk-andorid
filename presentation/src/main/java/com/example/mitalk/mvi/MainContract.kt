package com.example.mitalk.mvi

import com.example.mitalk.ui.util.EvaluateItemType
import java.util.UUID

data class MainState(
    val counsellorId: UUID? = null,
    val counsellorName: String? = null,
    val starCount: Int = 1,
    val evaluateComment: String = "",
    val goodEvaluationSelected1: EvaluateItemType? = null,
    val goodEvaluationSelected2: EvaluateItemType? = null,
    val badEvaluationSelected1: EvaluateItemType? = null,
    val badEvaluationSelected2: EvaluateItemType? = null,
)

sealed class MainSideEffect {
    object Logout : MainSideEffect()

    object ReviewSuccess : MainSideEffect()

    data class RemainRoom(val roomId: String) : MainSideEffect()

}