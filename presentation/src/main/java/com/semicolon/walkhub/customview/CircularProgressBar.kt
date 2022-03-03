package com.semicolon.walkhub.customview

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min


class CircularProgressBar(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    companion object {
        private const val DEFAULT_MAX_VALUE = 100f
    }

    private var rectF = RectF()
    private var transparentPaint: Paint = Paint().apply {
        color = 0
    }
    private var backgroundPaint: Paint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
    }
    private var foregroundPaint: Paint = Paint().apply {
        isAntiAlias = true
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
    }

    var progress: Float = 0f
        set(value) {
            field = if (progress <= progressMax) value else progressMax
            onProgressChangeListener?.invoke(progress)
            invalidate()
        }
    var progressMax: Float = DEFAULT_MAX_VALUE
        set(value) {
            field = if (field >= 0) value else DEFAULT_MAX_VALUE
            invalidate()
        }
    private var progressBarWidth: Float = 10f
        set(value) {
            field = value.dpToPx()
            foregroundPaint.strokeWidth = field
            requestLayout()
            invalidate()
        }
    private var backgroundProgressBarWidth: Float = 10f
        set(value) {
            field = value.dpToPx()
            backgroundPaint.strokeWidth = field
            requestLayout()
            invalidate()
        }
    private var progressBarColor: Int = Color.BLACK
        set(value) {
            field = value
            manageColor()
            invalidate()
        }
    private var backgroundProgressBarColor: Int = Color.GRAY
        set(value) {
            field = value
            manageBackgroundProgressBarColor()
            invalidate()
        }
    var onProgressChangeListener: ((Float) -> Unit)? = null

    init {
        progress = 0f
        progressMax = DEFAULT_MAX_VALUE
        progressBarWidth = 10f
        backgroundProgressBarWidth = 10f
        progressBarColor = Color.parseColor("#55A5DA")
        backgroundProgressBarColor = Color.parseColor("#F9F9F9")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        manageColor()
        manageBackgroundProgressBarColor()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawOval(rectF, transparentPaint)

        canvas.drawArc(
            rectF,
            120f,
            300f,
            false,
            backgroundPaint
        )

        if (progress > progressMax) progress = progressMax
        val realProgress = progress * DEFAULT_MAX_VALUE / progressMax
        val angle = 300 * realProgress / 100

        canvas.drawArc(
            rectF,
            120f,
            angle,
            false,
            foregroundPaint
        )
    }

    override fun setBackgroundColor(backgroundColor: Int) {
        backgroundProgressBarColor = backgroundColor
    }

    private fun manageColor() {
        foregroundPaint.color = progressBarColor
    }

    private fun manageBackgroundProgressBarColor() {
        backgroundPaint.color = backgroundProgressBarColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = min(width, height)
        setMeasuredDimension(min, min)
        val highStroke =
            if (progressBarWidth > backgroundProgressBarWidth) progressBarWidth else backgroundProgressBarWidth
        rectF.set(
            0 + highStroke / 2,
            0 + highStroke / 2,
            min - highStroke / 2,
            min - highStroke / 2
        )
    }

    private fun Float.dpToPx(): Float =
        this * Resources.getSystem().displayMetrics.density
}