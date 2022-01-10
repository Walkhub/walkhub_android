package com.semicolon.data.local.database.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class DateConverter {

    @TypeConverter
    fun dataTimeToString(localDateTime: LocalDateTime?): String? =
        localDateTime?.toString()

    @TypeConverter
    fun stringToDate(string: String?): Date? =
        if (string != null) {
            SimpleDateFormat("yyyy-MM-dd`T`HH-mm-ss", Locale.KOREA).parse(string)
        } else {
            null
        }
}