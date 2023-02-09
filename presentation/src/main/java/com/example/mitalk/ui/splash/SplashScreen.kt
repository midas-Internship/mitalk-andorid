package com.example.mitalk.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mitalk.util.theme.Medium21GM
import com.example.mitalk.util.theme.MitalkIcon

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