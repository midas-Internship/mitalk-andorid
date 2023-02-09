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
    }
}