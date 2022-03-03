package com.semicolon.walkhub.customview

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import com.semicolon.walkhub.R
import com.semicolon.walkhub.ui.base.*
import kotlin.math.max
import kotlin.math.min

@Composable
fun Dropdown(
    modifier: Modifier = Modifier,
    items: Array<String>,
    defaultItemIndex: Int? = null,
    placeholder: String = "",
    menuDirection: MenuDirection = MenuDirection.CENTER,
    onItemSelected: (index: Int, item: String) -> Unit = { _, _ -> }
) {
    var expandedState by remember { mutableStateOf(false) }
    var iconDegree by remember { mutableStateOf(180) }
    var selectorText by remember {
        mutableStateOf(if (defaultItemIndex == null) placeholder else items[defaultItemIndex])
    }
    var selectorSize by remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
    ) {
        DropdownSelector(
            modifier = modifier,
            text = selectorText,
            iconDegree = iconDegree,
            onSizeChanged = {
                selectorSize = it
            }
        ) {
            expandedState = !expandedState
            iconDegree = 0
        }
        DropdownMenu(
            expanded = expandedState,
            selectorWidth = selectorSize,
            items = items,
            direction = menuDirection,
            onDismissRequest = {
                expandedState = false
                iconDegree = 180
            }
        ) { index, item ->
            onItemSelected(index, item)
            selectorText = item
            iconDegree = 180
        }
    }
}

@Composable
fun DropdownSelector(
    modifier: Modifier = Modifier,
    iconDegree: Int,
    text: String,
    onSizeChanged: (dpSize: Float) -> Unit,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    var viewWidth by remember { mutableStateOf(0) }
    var viewDpWidth by remember { mutableStateOf(0f) }
    viewDpWidth = LocalDensity.current.run { viewWidth.toDp() }.value
    val textWidth = viewDpWidth.minus(24).dp
    onSizeChanged(viewDpWidth.plus(28))
    Row(
        modifier = modifier
            .height(36.dp)
            .widthIn(min = 104.dp)
            .clip(RoundedCornerShape(4.dp))
            .padding(start = 16.dp, end = 12.dp)
            .onSizeChanged { viewWidth = it.width }
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = gray700,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(textWidth)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Icon(
            modifier = Modifier
                .size(16.dp)
                .rotate(iconDegree.toFloat()),
            painter = painterResource(id = R.drawable.ic_spinner_down),
            contentDescription = ""
        )
    }
}

@Composable
fun DropdownMenu(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    selectorWidth: Float,
    items: Array<String>,
    direction: MenuDirection,
    onDismissRequest: () -> Unit,
    onMenuItemClick: (index: Int, item: String) -> Unit
) {
    var changedPxWidth by remember { mutableStateOf(0) }
    var width by remember { mutableStateOf(selectorWidth) }
    val changedWidth = LocalDensity.current.run { changedPxWidth.toDp() }.value
    if (changedWidth.toInt() > width.toInt()) {
        width = changedWidth
    }
    var offset by remember { mutableStateOf(DpOffset(0.dp, 0.dp)) }
    offset = when (direction) {
        MenuDirection.LEFT -> {
            val x = selectorWidth - changedWidth
            DpOffset(x.dp, 0.dp)
        }
        MenuDirection.CENTER -> {
            val x = (selectorWidth - changedWidth) / 2
            DpOffset(x.dp, 0.dp)
        }
        MenuDirection.RIGHT -> {
            DpOffset(0.dp, 0.dp)
        }
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() },
        offset = offset,
        modifier = modifier
            .padding(top = 4.dp)
            .wrapContentHeight()
            .wrapContentHeight()
            .sizeIn(minWidth = selectorWidth.dp)
            .onSizeChanged { changedPxWidth = it.width }
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, gray300, RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(vertical = 4.dp)

    ) {
        for (i in items.indices) {
            val item = items[i]
            DropdownMenuItem(
                text = item,
                parentWidth = width,
                onClick = {
                    onMenuItemClick(i, item)
                    onDismissRequest()
                })
        }
    }
}

@Composable
fun DropdownMenuItem(
    text: String,
    parentWidth: Float,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val itemColor = if (isPressed.value) primary else Color.White
    val textColor = if (isPressed.value) Color.White else gray700
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .sizeIn(minWidth = parentWidth.dp)
            .height(28.dp)
            .background(itemColor)
            .padding(horizontal = 16.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                onClick()
            },
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text, fontSize = 14.sp, color = textColor)
    }
}

@Composable
fun DropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset,
    properties: PopupProperties = PopupProperties(focusable = true),
    content: @Composable ColumnScope.() -> Unit
) {
    val expandedStates = remember { MutableTransitionState(false) }
    expandedStates.targetState = expanded

    if (expandedStates.currentState || expandedStates.targetState) {
        val transformOriginState = remember { mutableStateOf(TransformOrigin.Center) }
        val density = LocalDensity.current
        val popupPositionProvider = DropdownMenuPositionProvider(
            offset,
            density
        ) { parentBounds, menuBounds ->
            transformOriginState.value = calculateTransformOrigin(parentBounds, menuBounds)
        }

        Popup(
            onDismissRequest = onDismissRequest,
            popupPositionProvider = popupPositionProvider,
            properties = properties
        ) {
            Column(
                modifier = modifier
            ) {
                content()
            }
        }
    }
}

internal fun calculateTransformOrigin(
    parentBounds: IntRect,
    menuBounds: IntRect
): TransformOrigin {
    val pivotX = when {
        menuBounds.left >= parentBounds.right -> 0f
        menuBounds.right <= parentBounds.left -> 1f
        menuBounds.width == 0 -> 0f
        else -> {
            val intersectionCenter =
                (
                        max(parentBounds.left, menuBounds.left) +
                                min(parentBounds.right, menuBounds.right)
                        ) / 2
            (intersectionCenter - menuBounds.left).toFloat() / menuBounds.width
        }
    }
    val pivotY = when {
        menuBounds.top >= parentBounds.bottom -> 0f
        menuBounds.bottom <= parentBounds.top -> 1f
        menuBounds.height == 0 -> 0f
        else -> {
            val intersectionCenter =
                (
                        max(parentBounds.top, menuBounds.top) +
                                min(parentBounds.bottom, menuBounds.bottom)
                        ) / 2
            (intersectionCenter - menuBounds.top).toFloat() / menuBounds.height
        }
    }
    return TransformOrigin(pivotX, pivotY)
}

internal class DropdownMenuPositionProvider(
    private val contentOffset: DpOffset,
    private val density: Density,
    val onPositionCalculated: (IntRect, IntRect) -> Unit = { _, _ -> }
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        // The min margin above and below the menu, relative to the screen.
        val verticalMargin = with(density) { 48.dp.roundToPx() }
        // The content offset specified using the dropdown offset parameter.
        val contentOffsetX = with(density) { contentOffset.x.roundToPx() }
        val contentOffsetY = with(density) { contentOffset.y.roundToPx() }

        // Compute horizontal position.
        val toRight = anchorBounds.left + contentOffsetX
        val toLeft = anchorBounds.right - contentOffsetX - popupContentSize.width
        val toDisplayRight = windowSize.width - popupContentSize.width
        val toDisplayLeft = 0
        val x = if (layoutDirection == LayoutDirection.Ltr) {
            sequenceOf(toRight, toLeft, toDisplayRight)
        } else {
            sequenceOf(toLeft, toRight, toDisplayLeft)
        }.firstOrNull {
            it >= 0 && it + popupContentSize.width <= windowSize.width
        } ?: toLeft

        // Compute vertical position.
        val toBottom = maxOf(anchorBounds.bottom + contentOffsetY, verticalMargin)
        val toTop = anchorBounds.top - contentOffsetY - popupContentSize.height
        val toCenter = anchorBounds.top - popupContentSize.height / 2
        val toDisplayBottom = windowSize.height - popupContentSize.height - verticalMargin
        val y = sequenceOf(toBottom, toTop, toCenter, toDisplayBottom).firstOrNull {
            it >= verticalMargin &&
                    it + popupContentSize.height <= windowSize.height - verticalMargin
        } ?: toTop

        onPositionCalculated(
            anchorBounds,
            IntRect(x, y, x + popupContentSize.width, y + popupContentSize.height)
        )
        return IntOffset(x, y)
    }
}

enum class MenuDirection {
    LEFT, CENTER, RIGHT
}