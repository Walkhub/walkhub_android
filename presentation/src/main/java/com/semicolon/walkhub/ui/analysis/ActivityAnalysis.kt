package com.semicolon.walkhub.ui.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semicolon.walkhub.ui.base.Body2
import com.semicolon.walkhub.ui.base.Body3
import com.semicolon.walkhub.ui.base.Subtitle4
import com.semicolon.walkhub.ui.base.gray800
import org.threeten.bp.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun ActivityAnalysis() {
    // TODO: ViewModel 완성 후 더미데이터 지우고 값 넣기
    val scrollState = rememberScrollState()
    val random = Random()
    val items = ArrayList<GraphItem>()
    for (i in 1..28) {
        items.add(GraphItem(random.nextInt(15000), LocalDate.of(2022, 1, i)))
    }
    Box(
        Modifier
            .background(Color(0xFFF9F9F9))
            .verticalScroll(scrollState)
    ) {
        Column(
            Modifier
                .padding(top = 260.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White, RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                .padding(top = 120.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 40.dp)
            ) {
                val date = LocalDate.now()
                Body2(
                    text = "${date.month.value}월 ${date.dayOfMonth}일 (${date.dayOfWeek.toKoreanString()})",
                    color = Color(0xFF8E8E8E)
                )
                Spacer(modifier = Modifier.size(12.dp))
                Body2(text = "걸음 수", color = gray800)
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Subtitle4(text = "6472")
                    Spacer(modifier = Modifier.size(4.dp))
                    Body2(
                        text = "/7000 걸음", color = gray800,
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                ProgressBar(
                    percent = 0.924f,
                    backgroundColor = Color(0xFFE5E5E5),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.size(12.dp))
                Body2(text = "칼로리 소모", color = gray800)
                Body3(text = "건강정보를 기준으로 측정했어요", color = gray800)
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Subtitle4(text = "203")
                    Spacer(modifier = Modifier.size(4.dp))
                    Body2(
                        text = "Kcal", color = gray800,
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.TopCenter
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 38.dp, bottom = 30.dp)
                        .height(28.dp)
                        .width(1.dp)
                        .background(Color(0xFFE3E3E3))
                )
                Row(
                    modifier = Modifier
                        .padding(top = 22.dp)
                        .wrapContentSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Body2(text = "거리", color = gray800)
                    Spacer(modifier = Modifier.width(138.dp))
                    Body2(text = "시간", color = gray800)
                }
                Row(
                    modifier = Modifier
                        .padding(top = 46.dp)
                        .wrapContentSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Subtitle4(text = "5.24", color = gray800)
                    Spacer(modifier = Modifier.width(4.dp))
                    Body2(
                        text = "km", color = gray800,
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                    Spacer(modifier = Modifier.width(105.dp))
                    Subtitle4(text = "1", color = gray800)
                    Spacer(modifier = Modifier.width(2.dp))
                    Body2(
                        text = "h", color = gray800,
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Subtitle4(text = "10", color = gray800)
                    Spacer(modifier = Modifier.width(2.dp))
                    Body2(
                        text = "m", color = gray800,
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var graphState by remember { mutableStateOf(GraphType.WEEKLY) }
                GraphToggleButton {
                    graphState =
                        if (graphState == GraphType.WEEKLY) GraphType.MONTHLY else GraphType.WEEKLY
                }
                Spacer(modifier = Modifier.height(8.dp))
                WalkGraph(items = items, type = graphState)
            }
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 28.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Body2(text = "걸음수 총합", color = gray800)
                    Subtitle4(text = "145382", color = gray800)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Body2(text = "평균 걸음수", color = gray800)
                    Subtitle4(text = "4214", color = gray800)
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppBar(text = "활동분석") {
                // TODO: "뒤로가기 눌렀을 때 로직"
            }
            Spacer(modifier = Modifier.size(12.dp))
            CaloriesLevelCard(
                imageUrl = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/43380b84-6dc9-44fc-8b51-6aef4d9f1faf/커피icon.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220217%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220217T062841Z&X-Amz-Expires=86400&X-Amz-Signature=1d6b84669e154680843a14e264df803d7cbe67a8d4de592dddb1826fcd6e9daf&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22%25EC%25BB%25A4%25ED%2594%25BCicon.png%22&x-id=GetObject",
                name = "카페 라떼",
                calories = 180,
                size = "355ml",
                level = 7,
                progress = 0.5f,
                message = "커피 한 잔 어때요~?"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActivityAnalysis() {
    ActivityAnalysis()
}