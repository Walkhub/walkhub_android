package com.semicolon.walkhub.ui.analysis

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semicolon.walkhub.ui.base.Button
import com.semicolon.walkhub.ui.base.primary


@Composable
fun GraphToggleButton(onItemChanged: (GraphType) -> Unit) {
    var isWeeklySelected by remember { mutableStateOf(true) }
    val cursorPaddingWidth by animateDpAsState(if (isWeeklySelected) 0.dp else 125.dp)
    Box {
        Spacer(
            modifier = Modifier
                .padding(start = cursorPaddingWidth)
                .width(81.dp)
                .height(28.dp)
                .background(color = primary, shape = RoundedCornerShape(14.dp))
        )

        Row {
            GraphToggleItem(text = "주간", isSelected = isWeeklySelected) {
                onItemChanged(GraphType.WEEKLY)
                isWeeklySelected = true
            }
            Spacer(modifier = Modifier.width(44.dp))
            GraphToggleItem(text = "월간", isSelected = !isWeeklySelected) {
                onItemChanged(GraphType.MONTHLY)
                isWeeklySelected = false
            }
        }
    }
}

@Composable
fun GraphToggleItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val textColor by animateColorAsState(if (isSelected) Color.White else Color(0xFFA2A2A2))
    Box(
        modifier = Modifier
            .width(81.dp)
            .height(28.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Button(text = text, color = textColor)
    }
}

@Preview(showBackground = true)
@Composable
fun GraphToggleButtonPreview() {
    GraphToggleButton {}
}