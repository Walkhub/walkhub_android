package com.semicolon.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.annotations.JsonAdapter
import com.semicolon.data.local.entity.rank.*
import java.sql.Types
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class RankListTypeConverter(
    private val moshi: Moshi
) {
    @TypeConverter
    fun fromStringOurSchoolRank(value: String): List<OurSchoolUserRankRoomEntity.Ranking>? {
        val listType = Types.newParameterizedType(List::class.java, OurSchoolUserRankRoomEntity.Ranking::class.java)
        val adapter: JsonAdapter<List<OurSchoolUserRankRoomEntity.Ranking>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromStringSchoolRank(value: String): List<SchoolRankRoomEntity.SchoolRank>? {
        val listType = Types.newParameterizedType(List::class.java, SchoolRankRoomEntity.SchoolRank::class.java)
        val adapter: JsonAdapter<List<SchoolRankRoomEntity.SchoolRank>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromStringUserRank(value: String): List<UserRankRoomEntity.UserRank>? {
        val listType = Types.newParameterizedType(List::class.java, UserRankRoomEntity.UserRank::class.java)
        val adapter: JsonAdapter<List<UserRankRoomEntity.UserRank>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromStringSearchUser(value: String): List<SearchUserRoomEntity.UserInfo>? {
        val listType = Types.newParameterizedType(List::class.java, SearchUserRoomEntity.UserInfo::class.java)
        val adapter: JsonAdapter<List<SearchUserRoomEntity.UserInfo>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromStringSearchSchool(value: String): List<SearchSchoolRoomEntity.SchoolInfo>? {
        val listType = Types.newParameterizedType(List::class.java, SearchSchoolRoomEntity.SchoolInfo::class.java)
        val adapter: JsonAdapter<List<SearchSchoolRoomEntity.SchoolInfo>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }
}