package com.semicolon.walkhub.ui.base

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun Title1(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 60,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W500,
        size = 40,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Title2(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 54,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W500,
        size = 36,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Title3(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 48,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W500,
        size = 32,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Subtitle1(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 48,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W400,
        size = 32,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Subtitle2(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 42,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W400,
        size = 28,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Subtitle3(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 36,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W400,
        size = 24,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Subtitle4(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 30,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W400,
        size = 20,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Body1(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 24,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W300,
        size = 16,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Body2(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 21,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W300,
        size = 14,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Body3(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 18,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W300,
        size = 12,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 21,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W300,
        size = 14,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Caption(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 18,
    letterSpacing: Int = 0,
    color: Color = Color.Black,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W300,
        size = 12,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun MiniCaption(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 12,
    letterSpacing: Int = 0,
    color: Color = Color.Black,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W300,
        size = 8,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Typography(
    text: String,
    modifier: Modifier = Modifier,
    weight: FontWeight,
    size: Int,
    color: Color,
    lineHeight: Int,
    letterSpacing: Int,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign?,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    onTextLayout: (TextLayoutResult) -> Unit
) {
    Text(
        text = text,
        modifier = modifier,
        fontWeight = weight,
        fontSize = size.sp,
        color = color,
        lineHeight = lineHeight.sp,
        letterSpacing = letterSpacing.sp,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}
