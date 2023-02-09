package com.example.mitalk.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.Medium21GM
import com.example.mitalk.util.theme.MitalkColor
import com.example.mitalk.util.theme.MitalkIcon
import com.example.mitalk.util.theme.Regular12NO

@Stable
private val BtnShape = RoundedCornerShape(8.dp)

@Composable
fun SplashScreen() {
    Column {
        Spacer(modifier = Modifier.height(93.dp))

        Image(
            painter = painterResource(id = MitalkIcon.Splash_Img.drawableId),
            contentDescription = MitalkIcon.Splash_Img.contentDescription,
            modifier = Modifier
                .padding(start = 28.dp)
                .fillMaxWidth()
                .height(300.dp)
        )
        
        Spacer(modifier = Modifier.height(10.dp))
        
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
        ) {
            CommaAboveText(text = "편 ")
            CommaAboveText(text = "리 ")
            CommaAboveText(text = "한 ")
            Medium21GM(text = "   고 객   문 의")
        }

        Spacer(modifier = Modifier.height(69.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    color = MitalkColor.White,
                    shape = BtnShape
                )
                .clip(shape = BtnShape)
                .border(
                    width = 2.dp,
                    shape = BtnShape,
                    color = Color(0xFFA69E9E)
                )
                .miClickable { },
            contentAlignment = Alignment.Center,
        ) {
            Row {
               Image(
                   painter = painterResource(id = MitalkIcon.Google_Icon.drawableId),
                   contentDescription = MitalkIcon.Google_Icon.contentDescription,
                   modifier = Modifier.size(28.dp),
               )
                
                Spacer(modifier = Modifier.width(10.dp))

                Regular12NO(text = "Continue with Google")
            }
        }

    }
}

@Composable
fun CommaAboveText(text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = MitalkIcon.Comma.drawableId),
            contentDescription = MitalkIcon.Comma.contentDescription,
        )
        Medium21GM(text = text)
    }
}
@Composable
@Preview(showBackground = true)
fun ShowSplashScreen() {
    SplashScreen()
}