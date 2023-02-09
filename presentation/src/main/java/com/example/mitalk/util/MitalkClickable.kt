package com.example.mitalk.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.miClickable(
    rippleEnabled: Boolean = true,
    rippleColor: Color? = null,
    runIf: Boolean = true,
    singleClick: Boolean = true,
    onLongClick: (() -> Unit)? = null,
    onClick: (() -> Unit)?,
) = runIf(runIf) {
    composed {
        val multipleEventsCutter = remember { MultipleEventsCutter.get() }

        combinedClickable(
            onClick = {
                onClick?.let {
                    if (singleClick) {
                        multipleEventsCutter.processEvent {
                            it()
                        }
                    } else {
                        it()
                    }
                }
            },
            onLongClick = onLongClick,
            indication = rememberRipple(
                color = rippleColor ?: Color.Unspecified,
            ).takeIf {
                rippleEnabled
            },
            interactionSource = remember { MutableInteractionSource() },
        )
    }
}

fun Modifier.simSelectable(
    selected: Boolean,
    rippleEnabled: Boolean = true,
    rippleColor: Color? = null,
    enabled: Boolean = true,
    role: Role? = null,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "selectable"
        properties["selected"] = selected
        properties["enabled"] = enabled
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    Modifier.selectable(
        selected = selected,
        enabled = enabled,
        role = role,
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(
            color = rippleColor ?: Color.Unspecified,
        ).takeIf {
            rippleEnabled
        },
        onClick = onClick
    )
}

private const val ClickEventDelayTime: Long = 300L

internal interface MultipleEventsCutter {
    fun processEvent(event: () -> Unit)

    companion object
}

internal fun MultipleEventsCutter.Companion.get(): MultipleEventsCutter =
    MultipleEventsCutterImpl()

private class MultipleEventsCutterImpl : MultipleEventsCutter {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= ClickEventDelayTime) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}

internal inline fun <T> T.runIf(condition: Boolean, run: T.() -> T) = if (condition) {
    run()
} else this
