package com.semicolon.walkhub.ui.analysis

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semicolon.walkhub.R
import com.semicolon.walkhub.ui.base.Body1
import com.semicolon.walkhub.ui.base.gray800

@Composable
fun AppBar(text: String, onBackButtonClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.Transparent)
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    onBackButtonClick
                },
            painter = painterResource(R.drawable.ic_back_arrow),
            contentDescription = null
        )

        Body1(
            text = text,
            color = gray800,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    AppBar(text = "활동분석") {}
}