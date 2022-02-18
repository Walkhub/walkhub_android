package com.semicolon.walkhub.ui.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semicolon.walkhub.ui.base.primary

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    percent: Float,
    barColor: Color = primary,
    backgroundColor: Color = Color.White
) {
    var barMaxWidth by remember { mutableStateOf(0) }
    val barWidth = LocalDensity.current.run {
        barMaxWidth.toDp().value * if (percent > 1) 1f else if (percent < 0) 0f else percent
    }

    Box(
        modifier = modifier
            .height(11.dp)
            .background(backgroundColor, RoundedCornerShape(5.5.dp))
            .padding(horizontal = 3.dp)
            .onSizeChanged {
                barMaxWidth = it.width
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Spacer(
            modifier = Modifier
                .width(barWidth.dp)
                .height(5.dp)
                .background(color = barColor, RoundedCornerShape(2.5.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressBarPreview() {
    ProgressBar(percent = 0.5f, backgroundColor = Color(0xFFE5E5E5))
}
