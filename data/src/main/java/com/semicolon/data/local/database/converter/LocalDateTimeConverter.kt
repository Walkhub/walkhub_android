package com.semicolon.data.local.database.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun localDateTimeToString(localDateTime: LocalDateTime?): String? =
        localDateTime?.toString()

    @TypeConverter
    fun stringToLocalDateTime(string: String?): LocalDateTime? =
        LocalDateTime.parse(string)
}