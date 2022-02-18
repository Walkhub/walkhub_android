package com.semicolon.walkhub.ui.analysis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.semicolon.walkhub.ui.base.Body1
import com.semicolon.walkhub.ui.base.Body3
import com.semicolon.walkhub.ui.base.Subtitle4
import com.semicolon.walkhub.ui.base.primary

@Composable
fun CaloriesLevelCard(
    imageUrl: String,
    name: String,
    calories: Int,
    size: String,
    level: Int,
    progress: Float,
    message: String,
) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(272.dp)
            .background(
                color = primary,
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 64.dp,
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )
            )
    ) {
        Spacer(
            Modifier
                .padding(top = 20.dp, start = 68.dp)
                .size(112.dp)
                .shadow(8.dp, RoundedCornerShape(56.dp))
        )

        Box(
            modifier = Modifier
                .padding(top = 14.dp, start = 74.dp)
                .size(112.dp)
                .background(Color.White, RoundedCornerShape(56.dp))
                .padding(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 18.dp, top = 136.dp)
                .wrapContentSize()
        ) {
            Body1(text = name, color = Color.White)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Subtitle4(text = calories.toString(), color = Color.White)
                Body1(text = "kcal", color = Color.White)
                Spacer(modifier = Modifier.width(5.dp))
                Body3(text = "(${size} 기준)", color = Color.White)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Body3(text = message, color = Color.White)
        }

        Box(
            modifier = Modifier
                .padding(start = 18.dp, top = 240.dp)
                .wrapContentSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            ProgressBar(
                modifier = Modifier
                    .padding(start = 32.dp)
                    .width(132.dp),
                percent = progress
            )
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(20.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Body3(text = "Lv.${level}", color = primary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CaloriesLevelCardPreview() {
    CaloriesLevelCard(
        "",
        "카페 라떼",
        180,
        "355ml",
        7,
        0.5f,
        "커피 한 잔 할래요~?"
    )
}