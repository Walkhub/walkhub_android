package com.semicolon.data.local.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.semicolon.domain.enum.ChallengeScope
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {

    @TypeConverter
    fun dataTimeToString(date: Date?): String? =
        date?.toString()

    @TypeConverter
    fun stringToDate(string: String?): Date? =
        if (string != null) {
            SimpleDateFormat("yyyy-MM-dd`T`HH-mm-ss", Locale.KOREA).parse(string)
        } else {
            null
        }
}