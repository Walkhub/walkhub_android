package com.semicolon.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

//@ProvidedTypeConverter
//class BadgeListTypeConverter(
//    private val moshi: Moshi
//) {
//
//    @TypeConverter
//    fun fromString(value: String): List<UserOwnBadgeRoomEntity.Badge>? {
//        val listType = Types.newParameterizedType(List::class.java, UserOwnBadgeRoomEntity.Badge::class.java)
//        val adapter: JsonAdapter<List<UserOwnBadgeRoomEntity.Badge>> = moshi.adapter(listType)
//        return adapter.fromJson(value)
//    }
//
//    @TypeConverter
//    fun fromImage(type: List<UserOwnBadgeRoomEntity.Badge>): String {
//        val listType = Types.newParameterizedType(List::class.java, UserOwnBadgeRoomEntity.Badge::class.java)
//        val adapter: JsonAdapter<List<UserOwnBadgeRoomEntity.Badge>> = moshi.adapter(listType)
//        return adapter.toJson(type)
//    }
//
//}