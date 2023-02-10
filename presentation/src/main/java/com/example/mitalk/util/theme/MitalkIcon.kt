package com.example.mitalk.util.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import com.example.mitalk.R
import javax.annotation.concurrent.Immutable

@Immutable
class MitalkIcon private constructor(
    @DrawableRes val drawableId: Int,
    val contentDescription: String? = null,
) {
    companion object {
        @Stable
        val Splash_Img = MitalkIcon(
            drawableId = R.drawable.img_splash,
            contentDescription = "splash img"
        )

        @Stable
        val Google_Icon = MitalkIcon(
            drawableId = R.drawable.icon_google,
            contentDescription = "google icon",
        )

        @Stable
        val Comma = MitalkIcon(
            drawableId = R.drawable.comma,
            contentDescription = "comma",
        )

        @Stable
        val Back = MitalkIcon(
            drawableId = R.drawable.back,
            contentDescription = "back",
        )

        @Stable
        val Logo = MitalkIcon(
            drawableId = R.drawable.img_logo,
            contentDescription = "logo",
        )

        @Stable
        val Counselor_Img = MitalkIcon(
            drawableId = R.drawable.img_counselor,
            contentDescription = "counselor",
        )

        @Stable
        val Record_Img = MitalkIcon(
            drawableId = R.drawable.img_record,
            contentDescription = "record",
        )

        @Stable
        val Question_Img = MitalkIcon(
            drawableId = R.drawable.img_question,
            contentDescription = "question"
        )

        @Stable
        val Bulb_Img = MitalkIcon(
            drawableId = R.drawable.img_bulb,
            contentDescription = "bulb"
        )
    }
}