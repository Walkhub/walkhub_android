package com.semicolon.domain.enum

enum class MeasuringState(val id: Int) {
    ONGOING(0),
    PAUSED(1),
    FINISHED(2)
}

fun Int.toMeasuringState() =
    when (this) {
        0 -> MeasuringState.ONGOING
        1 -> MeasuringState.PAUSED
        else -> MeasuringState.FINISHED
    }