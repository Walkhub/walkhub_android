package com.semicolon.data.local.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.semicolon.data.local.entity.badge.FetchMyBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchNewBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchUserBadgesRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeDetailRoomEntity
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.data.local.entity.rank.*
import com.semicolon.data.local.entity.user.FetchCaloriesLevelRoomEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class CaloriesListTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
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
class NoticeListTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<NoticeListRoomEntity.NoticeListValue>? {
        val listType =
            Types.newParameterizedType(
                List::class.java,
                NoticeListRoomEntity.NoticeListValue::class.java
            )
        val adapter: JsonAdapter<List<NoticeListRoomEntity.NoticeListValue>> =
            moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<NoticeListRoomEntity.NoticeListValue>): String {
        val listType =
            Types.newParameterizedType(
                List::class.java,
                NoticeListRoomEntity.NoticeListValue::class.java
            )
        val adapter: JsonAdapter<List<NoticeListRoomEntity.NoticeListValue>> =
            moshi.adapter(listType)
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
class RankOurSchoolTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<OurSchoolUserRankRoomEntity.Ranking>? {
        val listType = Types.newParameterizedType(
            List::class.java,
            OurSchoolUserRankRoomEntity.Ranking::class.java
        )
        val adapter: JsonAdapter<List<OurSchoolUserRankRoomEntity.Ranking>> =
            moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<OurSchoolUserRankRoomEntity.Ranking>): String {
        val listType = Types.newParameterizedType(
            List::class.java,
            OurSchoolUserRankRoomEntity.Ranking::class.java
        )
        val adapter: JsonAdapter<List<OurSchoolUserRankRoomEntity.Ranking>> =
            moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class RankSchoolRankAndSearchTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<SchoolRankAndSearchRoomEntity.SchoolRank>? {
        val listType = Types.newParameterizedType(
            List::class.java,
            SchoolRankAndSearchRoomEntity.SchoolRank::class.java
        )
        val adapter: JsonAdapter<List<SchoolRankAndSearchRoomEntity.SchoolRank>> =
            moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<SchoolRankAndSearchRoomEntity.SchoolRank>): String {
        val listType = Types.newParameterizedType(
            List::class.java,
            SchoolRankAndSearchRoomEntity.SchoolRank::class.java
        )
        val adapter: JsonAdapter<List<SchoolRankAndSearchRoomEntity.SchoolRank>> =
            moshi.adapter(listType)
        return adapter.toJson(type)
    }
}

@ProvidedTypeConverter
class RankSearchUserTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<SearchUserRoomEntity.UserInfo>? {
        val listType =
            Types.newParameterizedType(List::class.java, SearchUserRoomEntity.UserInfo::class.java)
        val adapter: JsonAdapter<List<SearchUserRoomEntity.UserInfo>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<SearchUserRoomEntity.UserInfo>): String {
        val listType = Types.newParameterizedType(
            List::class.java,
            SearchUserRoomEntity.UserInfo::class.java
        )
        val adapter: JsonAdapter<List<SearchUserRoomEntity.UserInfo>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }

}

@ProvidedTypeConverter
class RankUserRankTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<UserRankRoomEntity.UserRank>? {
        val listType =
            Types.newParameterizedType(List::class.java, UserRankRoomEntity.UserRank::class.java)
        val adapter: JsonAdapter<List<UserRankRoomEntity.UserRank>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<UserRankRoomEntity.UserRank>): String {
        val listType = Types.newParameterizedType(
            List::class.java,
            UserRankRoomEntity.UserRank::class.java
        )
        val adapter: JsonAdapter<List<UserRankRoomEntity.UserRank>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }

}

@ProvidedTypeConverter
class ChallengeParticipantTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromString(value: String): List<ChallengeDetailRoomEntity.ParticipantList>? {
        val listType = Types.newParameterizedType(List::class.java, ChallengeDetailRoomEntity.ParticipantList::class.java)
        val adapter: JsonAdapter<List<ChallengeDetailRoomEntity.ParticipantList>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<ChallengeDetailRoomEntity.ParticipantList>): String {
        val listType = Types.newParameterizedType(
            List::class.java,
            ChallengeDetailRoomEntity.ParticipantList::class.java
        )
        val adapter: JsonAdapter<List<ChallengeDetailRoomEntity.ParticipantList>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }

}