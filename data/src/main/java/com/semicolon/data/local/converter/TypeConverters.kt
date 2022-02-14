package com.semicolon.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
<<<<<<< HEAD
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.data.local.entity.user.UserOwnBadgeRoomEntity
=======
import com.semicolon.data.local.entity.user.FetchCaloriesLevelRoomEntity
>>>>>>> develop
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class CaloriesListTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<FetchCaloriesLevelRoomEntity.CaloriesLevel>? {
        val listType = Types.newParameterizedType(List::class.java, FetchCaloriesLevelRoomEntity.CaloriesLevel::class.java)
        val adapter: JsonAdapter<List<FetchCaloriesLevelRoomEntity.CaloriesLevel>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<FetchCaloriesLevelRoomEntity.CaloriesLevel>): String {
        val listType = Types.newParameterizedType(List::class.java, FetchCaloriesLevelRoomEntity.CaloriesLevel::class.java)
        val adapter: JsonAdapter<List<FetchCaloriesLevelRoomEntity.CaloriesLevel>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
<<<<<<< HEAD

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

=======
>>>>>>> develop
}