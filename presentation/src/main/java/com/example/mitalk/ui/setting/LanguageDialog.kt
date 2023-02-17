package com.example.mitalk.vm.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mitalk.R
import com.example.mitalk.util.English
import com.example.mitalk.util.Korean
import com.example.mitalk.util.miClickable
import com.example.mitalk.util.theme.Bold20NO
import com.example.mitalk.util.theme.MitalkColor

@Composable
fun LanguageDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onChangeAction: (String) -> Unit
) {
    if (visible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .width(280.dp)
                    .height(170.dp)
                    .background(color = MitalkColor.White, shape = RoundedCornerShape(5.dp)),
                verticalArrangement = Arrangement.Center
            ) {
                Bold20NO(
                    text = stringResource(id = R.string.korean),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .miClickable {
                            onChangeAction(Korean)
                            onDismissRequest()
                        })
                Spacer(modifier = Modifier.height(20.dp))
                Bold20NO(
                    text = stringResource(id = R.string.english),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .miClickable {
                            onChangeAction(English)
                            onDismissRequest()
                        })
            }
        }
    }
}