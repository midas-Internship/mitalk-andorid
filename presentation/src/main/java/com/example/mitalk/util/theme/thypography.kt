package com.example.mitalk.util.theme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.mitalk.R

internal val notoSansKR = FontFamily(
    Font(R.font.noto_sans_kr_black, FontWeight.Black),
    Font(R.font.noto_sans_kr_bold, FontWeight.Bold),
    Font(R.font.noto_sans_kr_light, FontWeight.Light),
    Font(R.font.noto_sans_kr_medium, FontWeight.Medium),
    Font(R.font.noto_sans_kr_regular, FontWeight.Normal),
    Font(R.font.noto_sans_kr_thin, FontWeight.Thin),
)

internal val gmartketSans = FontFamily(
    Font(R.font.gmarket_sans_light, FontWeight.Light),
    Font(R.font.gmarket_sans_medium, FontWeight.Medium),
    Font(R.font.gmarket_sans_bold, FontWeight.Bold)
)

object MiTalkTypography {

    @Stable
    val regular12NO = TextStyle(
        fontFamily = notoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

    @Stable
    val medium21GM = TextStyle(
        fontFamily = gmartketSans,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
    )
}

@Composable
fun Regular12NO(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MitalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkTypography.regular12NO,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun Medium21GM(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MitalkColor.Black,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MiTalkTypography.medium21GM,
        color = color,
        textAlign = textAlign,
    )
}
