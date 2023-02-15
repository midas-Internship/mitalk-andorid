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
        val Close = MitalkIcon(
            drawableId = R.drawable.icon_close,
            contentDescription = "close",
        )

        @Stable
        val Logo = MitalkIcon(
            drawableId = R.drawable.img_logo,
            contentDescription = "logo",
        )

        @Stable
        val Plus = MitalkIcon(
            drawableId = R.drawable.ic_plus,
            contentDescription = "plus"
        )

        @Stable
        val Send = MitalkIcon(
            drawableId = R.drawable.ic_send,
            contentDescription = "send"
        )

        @Stable
        val Cancel = MitalkIcon(
            drawableId = R.drawable.ic_cancel,
            contentDescription = "cancel"
        )

        @Stable
        val Picture = MitalkIcon(
            drawableId = R.drawable.ic_picture,
            contentDescription = "picture"
        )

        @Stable
        val Video = MitalkIcon(
            drawableId = R.drawable.ic_video,
            contentDescription = "video"
        )

        @Stable
        val Document = MitalkIcon(
            drawableId = R.drawable.ic_document,
            contentDescription = "document"
        )

        @Stable
        val Counselor = MitalkIcon(
            drawableId = R.drawable.ic_counselor,
            contentDescription = "counselor"
        )

        @Stable
        val Search = MitalkIcon(
            drawableId = R.drawable.ic_search,
            contentDescription = "search"
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
        val Setting_Img = MitalkIcon(
            drawableId = R.drawable.img_setting,
            contentDescription = "setting"
        )

        @Stable
        val Logout_Img = MitalkIcon(
            drawableId = R.drawable.img_logout,
            contentDescription = "log out"
        )

        @Stable
        val Bulb_Img = MitalkIcon(
            drawableId = R.drawable.img_bulb,
            contentDescription = "bulb"
        )

        @Stable
        val Function_Suggest_Img = MitalkIcon(
            drawableId = R.drawable.img_function_suggest,
            contentDescription = "function_suggest"
        )

        @Stable
        val Bug_Report_Img = MitalkIcon(
            drawableId = R.drawable.img_bug_report,
            contentDescription = "bug_report"
        )

        @Stable
        val Function_Question_Img = MitalkIcon(
            drawableId = R.drawable.img_function_question,
            contentDescription = "function_question"
        )

        @Stable
        val Product_Feedback_Img = MitalkIcon(
            drawableId = R.drawable.img_product_feedback,
            contentDescription = "product_feedback"
        )

        @Stable
        val Alliance_Inquiry_Img = MitalkIcon(
            drawableId = R.drawable.img_alliance_inquiry,
            contentDescription = "alliance_inquiry"
        )

        @Stable
        val Etc_Img = MitalkIcon(
            drawableId = R.drawable.img_etc,
            contentDescription = "etc"
        )

        @Stable
        val Star_On = MitalkIcon(
            drawableId = R.drawable.img_star_on,
            contentDescription = "star on"
        )

        @Stable
        val Star_Off = MitalkIcon(
            drawableId = R.drawable.img_star_off,
            contentDescription = "star off"
        )

        @Stable
        val Two_Circle_Icon = MitalkIcon(
            drawableId = R.drawable.icon_two_circle,
            contentDescription = "two circle",
        )

        @Stable
        val Empty_Circle_Icon = MitalkIcon(
            drawableId = R.drawable.icon_empty_circle,
            contentDescription = "empty circle",
        )

        @Stable
        val Fill_Circle_Icon = MitalkIcon(
            drawableId = R.drawable.icon_fill_circle,
            contentDescription = "fill circle"
        )

        @Stable
        val Record_Icon = MitalkIcon(
            drawableId = R.drawable.icon_record,
            contentDescription = "icon record"
        )
    }
}