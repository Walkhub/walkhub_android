package com.semicolon.walkhub.ui.base

import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.semicolon.walkhub.R

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
        baselineToTop = 42,
        baselineToBottom = 18,
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
        baselineToTop = 38,
        baselineToBottom = 16,
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
        baselineToTop = 34,
        baselineToBottom = 14,
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
        baselineToTop = 34,
        baselineToBottom = 14,
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
        baselineToTop = 30,
        baselineToBottom = 12,
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
        baselineToTop = 26,
        baselineToBottom = 10,
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
        weight = FontWeight.Medium,
        size = 20,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        baselineToTop = 22,
        baselineToBottom = 8,
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
        weight = FontWeight.Medium,
        size = 16,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        baselineToTop = 18,
        baselineToBottom = 6,
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
        weight = FontWeight.Medium,
        size = 14,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        baselineToTop = 16,
        baselineToBottom = 5,
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
        weight = FontWeight.Normal,
        size = 12,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        baselineToTop = 14,
        baselineToBottom = 4,
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
        weight = FontWeight.Normal,
        size = 14,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        baselineToTop = 16,
        baselineToBottom = 5,
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
        weight = FontWeight.Normal,
        size = 12,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        baselineToTop = 14,
        baselineToBottom = 4,
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
        weight = FontWeight.Medium,
        size = 8,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        baselineToTop = 10,
        baselineToBottom =2,
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
    baselineToTop: Int,
    baselineToBottom: Int,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign?,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    onTextLayout: (TextLayoutResult) -> Unit
) {

    val baselineModifier = modifier.paddingFromBaseline(top = baselineToTop.sp, bottom = baselineToBottom.sp)
    Text(
        style = TextStyle(
            color = color,
            lineHeight = lineHeight.sp,
            letterSpacing = letterSpacing.sp,
            fontSize = size.sp,
            fontFamily = notoSansFamily,
            fontWeight = weight,
            textDecoration = textDecoration,
            textAlign = textAlign,
        ),
        text = text,
        modifier = baselineModifier,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

val notoSansFamily = FontFamily(
    Font(R.font.noto_sans_kr_black, FontWeight.Black),
    Font(R.font.noto_sans_kr_bold, FontWeight.Bold),
    Font(R.font.noto_sans_kr_medium, FontWeight.Medium),
    Font(R.font.noto_sans_kr_regular, FontWeight.Normal),
    Font(R.font.noto_sans_kr_light, FontWeight.Light),
    Font(R.font.noto_sans_kr_thin, FontWeight.Thin)
)
