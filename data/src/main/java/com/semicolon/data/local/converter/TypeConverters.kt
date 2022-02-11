package com.semicolon.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.semicolon.data.local.entity.user.FetchCaloriesLevelRoomEntity
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
}