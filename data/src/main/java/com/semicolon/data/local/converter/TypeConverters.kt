package com.semicolon.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.data.local.entity.user.UserOwnBadgeRoomEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class BadgeListTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<UserOwnBadgeRoomEntity.Badge>? {
        val listType = Types.newParameterizedType(List::class.java, UserOwnBadgeRoomEntity.Badge::class.java)
        val adapter: JsonAdapter<List<UserOwnBadgeRoomEntity.Badge>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromImage(type: List<UserOwnBadgeRoomEntity.Badge>): String {
        val listType = Types.newParameterizedType(List::class.java, UserOwnBadgeRoomEntity.Badge::class.java)
        val adapter: JsonAdapter<List<UserOwnBadgeRoomEntity.Badge>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }

}

@ProvidedTypeConverter
class NoticeListTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromStringToNotice(value: String): List<NoticeListRoomEntity>? {
        val listType = Types.newParameterizedType(List::class.java, NoticeListRoomEntity::class.java)
        val adapter: JsonAdapter<List<NoticeListRoomEntity>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

}