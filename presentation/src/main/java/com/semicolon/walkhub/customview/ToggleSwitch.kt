package com.semicolon.walkhub.customview

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.semicolon.walkhub.ui.base.gray300
import com.semicolon.walkhub.ui.base.primary

@Composable
fun ToggleSwitch(
    modifier: Modifier = Modifier,
    defaultState: ToggleState = ToggleState.TOGGLE_OFF,
    onToggleOn: () -> Unit = {},
    onToggleOff: () -> Unit = {}
) {
    val isToggleOn = remember { mutableStateOf(defaultState == ToggleState.TOGGLE_ON) }
    val backgroundColor: Color by animateColorAsState(if (isToggleOn.value) primary else gray300)
    val spaceSize: Dp by animateDpAsState(if (isToggleOn.value) 20.dp else 0.dp)
    Row(
        modifier = modifier
            .height(20.dp)
            .width(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(1.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                isToggleOn.value = !isToggleOn.value
                if (isToggleOn.value) onToggleOn() else onToggleOff()
            }
    ) {
        Spacer(modifier = Modifier.size(spaceSize))
        Spacer(
            modifier = Modifier
                .size(18.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToggleSwitchPreview() {
    ToggleSwitch(
        defaultState = ToggleState.TOGGLE_OFF
    )
}

enum class ToggleState {
    TOGGLE_ON, TOGGLE_OFF
}