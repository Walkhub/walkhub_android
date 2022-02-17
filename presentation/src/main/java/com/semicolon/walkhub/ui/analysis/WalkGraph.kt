package com.semicolon.walkhub.ui.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semicolon.walkhub.ui.base.Caption
import com.semicolon.walkhub.ui.base.MiniCaption
import com.semicolon.walkhub.ui.base.gray800
import com.semicolon.walkhub.ui.base.primary
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun WalkGraph(items: List<GraphItem>, type: GraphType) {
    if (items.size != 28) return
    val maxStep = items.maxOf { it.steps }
    val maxOfXAxis =
        when {
            maxStep < 5000 -> 5000
            maxStep % 5000 == 0 -> maxStep
            else -> (maxStep / 5000 + 1) * 5000
        }
    val lines = maxOfXAxis / 5000
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .height(110.dp)
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                val count = lines + 1
                items(count = count) {
                    if (lines - it == 0) Spacer(Modifier.size(9.dp))
                    else Caption(text = (5000 * (lines - it)).toString(), color = gray800)

                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .height(110.dp)
                    .width(228.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .width(228.dp)
                        .height(110.dp)
                        .padding(top = 9.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    val count = lines + 1
                    items(count = count) {
                        val color = if (lines - it == 0) Color.Transparent else Color(0xFFE6E6E6)
                        Spacer(
                            modifier = Modifier
                                .width(224.dp)
                                .height(1.dp)
                                .background(color)
                        )
                    }
                }
                when (type) {
                    GraphType.WEEKLY -> {
                        WeeklyGraph(items = items, maxOfXAxis = maxOfXAxis)
                    }
                    GraphType.MONTHLY -> {
                        MonthlyGraph(items = items, maxOfXAxis = maxOfXAxis)
                    }
                }
            }
        }
        when (type) {
            GraphType.WEEKLY -> {
                Row {
                    WeeklyGraphLabel(items = items)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            GraphType.MONTHLY -> {
                MonthlyGraphLabel(items = items)
            }
        }
    }
}

@Composable
fun WeeklyGraph(items: List<GraphItem>, maxOfXAxis: Int) {
    val weeklyItems = items.slice(items.lastIndex - 6..items.lastIndex)
    LazyRow(
        modifier = Modifier
            .width(228.dp)
            .height(110.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        items(count = weeklyItems.size) {
            val itemHeight = 110f * (weeklyItems[it].steps.toFloat() / maxOfXAxis.toFloat())
            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .height(itemHeight.dp)
                    .background(primary, RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
            )
        }
    }
}

@Composable
fun MonthlyGraph(items: List<GraphItem>, maxOfXAxis: Int) {
    val colors = listOf(
        Color(0xFF7800BC),
        Color(0xFF06BC00),
        Color(0xFFF1B957),
        Color(0xFFF157AE),
        primary
    )
    var colorIndex = 0
    LazyRow(
        modifier = Modifier
            .width(228.dp)
            .height(110.dp)
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        items(count = items.size) {
            val itemHeight = 110f * (items[it].steps.toFloat() / maxOfXAxis.toFloat())
            Spacer(
                modifier = Modifier
                    .width(4.dp)
                    .height(itemHeight.dp)
                    .background(
                        colors[colorIndex],
                        RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp)
                    )
            )
            if (items[it].date.dayOfWeek == DayOfWeek.SUNDAY) colorIndex += 1
        }
    }
}

@Composable
fun WeeklyGraphLabel(items: List<GraphItem>) {
    val weeklyItems = items.slice(items.lastIndex - 6..items.lastIndex)
    LazyRow(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .width(228.dp)
            .wrapContentHeight()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items(count = weeklyItems.size) {
            val text = weeklyItems[it].date.dayOfWeek.toKoreanString()
            Caption(text = text, color = gray800)
        }
    }
}

@Composable
fun MonthlyGraphLabel(items: List<GraphItem>) {
    val labelIndex = ArrayList<Int>()
    items.mapIndexed { index, graphItem ->
        if (graphItem.date.dayOfWeek == DayOfWeek.MONDAY)
            labelIndex.add(index)
    }

    val firstLabelDate = items[labelIndex[0]].date
    val secondLabelDate = items[labelIndex[1]].date
    val thirdLabelDate = items[labelIndex[2]].date
    val fourthLabelDate = items[labelIndex[3]].date
    val density = LocalDensity.current
    val centerOfFirstLabel = 16 + 6 + labelIndex[0] * 8

    var firstLabelWidth by remember { mutableStateOf(0) }
    var secondLabelWidth by remember { mutableStateOf(0) }
    var thirdLabelWidth by remember { mutableStateOf(0) }
    var fourthLabelWidth by remember { mutableStateOf(0) }

    val firstDatePadding = density.run {
        centerOfFirstLabel - ((firstLabelWidth / 2).toDp().value)
    }
    val secondDatePadding = density.run {
        centerOfFirstLabel + 56 - ((secondLabelWidth / 2).toDp().value)
    }
    val thirdDatePadding = density.run {
        centerOfFirstLabel + 112 - ((thirdLabelWidth / 2).toDp().value)
    }
    val fourthDatePadding = density.run {
        centerOfFirstLabel + 168 - ((fourthLabelWidth / 2).toDp().value)
    }
    Box(
        Modifier
            .padding(vertical = 4.dp)
            .width(260.dp)
            .wrapContentHeight()
    ) {
        MiniCaption(
            modifier = Modifier
                .padding(start = firstDatePadding.dp)
                .wrapContentSize()
                .onSizeChanged {
                    firstLabelWidth = it.width
                },
            text = "${firstLabelDate.month.value}.${firstLabelDate.dayOfMonth}",
            color = gray800
        )
        MiniCaption(
            modifier = Modifier
                .padding(start = secondDatePadding.dp)
                .wrapContentSize()
                .onSizeChanged {
                    secondLabelWidth = it.width
                },
            text = "${secondLabelDate.month.value}.${secondLabelDate.dayOfMonth}",
            color = gray800
        )
        MiniCaption(
            modifier = Modifier
                .padding(start = thirdDatePadding.dp)
                .wrapContentSize()
                .onSizeChanged {
                    thirdLabelWidth = it.width
                },
            text = "${thirdLabelDate.month.value}.${thirdLabelDate.dayOfMonth}",
            color = gray800
        )
        MiniCaption(
            modifier = Modifier
                .padding(start = fourthDatePadding.dp)
                .wrapContentSize()
                .onSizeChanged {
                    fourthLabelWidth = it.width
                },
            text = "${fourthLabelDate.month.value}.${fourthLabelDate.dayOfMonth}",
            color = gray800
        )
    }
}

data class GraphItem(
    val steps: Int,
    val date: LocalDate
)

fun DayOfWeek.toKoreanString(): String =
    when (this) {
        DayOfWeek.MONDAY -> "월"
        DayOfWeek.TUESDAY -> "화"
        DayOfWeek.WEDNESDAY -> "수"
        DayOfWeek.THURSDAY -> "목"
        DayOfWeek.FRIDAY -> "금"
        DayOfWeek.SATURDAY -> "토"
        DayOfWeek.SUNDAY -> "일"
    }

@Preview(showBackground = true)
@Composable
fun WalkGraphPreview() {
    val random = Random()
    val items = ArrayList<GraphItem>()
    for (i in 1..28) {
        items.add(GraphItem(random.nextInt(15000), LocalDate.of(2022, 1, i)))
    }
    Column {
        WalkGraph(items = items, type = GraphType.WEEKLY)
        WalkGraph(items = items, type = GraphType.MONTHLY)
    }
}