package com.semicolon.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.data.local.entity.user.UserOwnBadgeRoomEntity
import com.semicolon.data.local.entity.badge.FetchMyBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchNewBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchUserBadgesRoomEntity
import com.semicolon.data.local.entity.user.FetchCaloriesLevelRoomEntity
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

    fun fromString(value: String): List<FetchCaloriesLevelRoomEntity.CaloriesLevel>? {
        val listType = Types.newParameterizedType(
            List::class.java,
            FetchCaloriesLevelRoomEntity.CaloriesLevel::class.java
        )
        val adapter: JsonAdapter<List<FetchCaloriesLevelRoomEntity.CaloriesLevel>> =
            moshi.adapter(listType)

        return adapter.fromJson(value)
    }

    @TypeConverter

    fun fromImage(type: List<UserOwnBadgeRoomEntity.Badge>): String {
        val listType = Types.newParameterizedType(List::class.java, UserOwnBadgeRoomEntity.Badge::class.java)
        val adapter: JsonAdapter<List<UserOwnBadgeRoomEntity.Badge>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }

    fun fromList(type: List<FetchCaloriesLevelRoomEntity.CaloriesLevel>): String {
        val listType = Types.newParameterizedType(
            List::class.java,
            FetchCaloriesLevelRoomEntity.CaloriesLevel::class.java
        )
        val adapter: JsonAdapter<List<FetchCaloriesLevelRoomEntity.CaloriesLevel>> =
            moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class MyBadgeListTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<FetchMyBadgesRoomEntity.Badge>? {
        val listType =
            Types.newParameterizedType(List::class.java, FetchMyBadgesRoomEntity.Badge::class.java)
        val adapter: JsonAdapter<List<FetchMyBadgesRoomEntity.Badge>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<FetchMyBadgesRoomEntity.Badge>): String {
        val listType =
            Types.newParameterizedType(List::class.java, FetchMyBadgesRoomEntity.Badge::class.java)
        val adapter: JsonAdapter<List<FetchMyBadgesRoomEntity.Badge>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class NewBadgeListTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<FetchNewBadgesRoomEntity.Badge>? {
        val listType =
            Types.newParameterizedType(List::class.java, FetchNewBadgesRoomEntity.Badge::class.java)
        val adapter: JsonAdapter<List<FetchNewBadgesRoomEntity.Badge>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<FetchNewBadgesRoomEntity.Badge>): String {
        val listType =
            Types.newParameterizedType(List::class.java, FetchNewBadgesRoomEntity.Badge::class.java)
        val adapter: JsonAdapter<List<FetchNewBadgesRoomEntity.Badge>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class UserBadgeListTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<FetchUserBadgesRoomEntity.Badge>? {
        val listType = Types.newParameterizedType(
            List::class.java,
            FetchUserBadgesRoomEntity.Badge::class.java
        )
        val adapter: JsonAdapter<List<FetchUserBadgesRoomEntity.Badge>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<FetchUserBadgesRoomEntity.Badge>): String {
        val listType = Types.newParameterizedType(
            List::class.java,
            FetchUserBadgesRoomEntity.Badge::class.java
        )
        val adapter: JsonAdapter<List<FetchUserBadgesRoomEntity.Badge>> = moshi.adapter(listType)
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