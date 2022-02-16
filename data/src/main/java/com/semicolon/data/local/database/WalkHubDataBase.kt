package com.semicolon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.semicolon.data.local.converter.*
import com.semicolon.data.local.dao.*
import com.semicolon.data.local.entity.badge.FetchMyBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchNewBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchUserBadgesRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeDetailRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeParticipantRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.data.local.entity.rank.*
import com.semicolon.data.local.entity.user.FetchCaloriesLevelRoomEntity
import com.semicolon.data.local.entity.user.UserMyPageRoomEntity
import com.semicolon.data.local.entity.user.UserProfileRoomEntity

@Database(
    entities = [
        ChallengeRoomEntity::class,
        ChallengeDetailRoomEntity::class,
        ChallengeParticipantRoomEntity::class,
        UserMyPageRoomEntity::class,
        UserProfileRoomEntity::class,
        FetchCaloriesLevelRoomEntity::class,
        FetchMyBadgesRoomEntity::class,
        FetchNewBadgesRoomEntity::class,
        FetchUserBadgesRoomEntity::class,
        NoticeListRoomEntity::class,
        OurSchoolUserRankRoomEntity::class,
        SchoolRankRoomEntity::class,
        SearchSchoolRoomEntity::class,
        SearchUserRoomEntity::class,
        UserRankRoomEntity::class
    ], version = 1, exportSchema = false
)

@TypeConverters(
    value = [
        NoticeListTypeConverter::class,
        CaloriesListTypeConverter::class,
        MyBadgeListTypeConverter::class,
        NewBadgeListTypeConverter::class,
        UserBadgeListTypeConverter::class,
        RankListTypeConverter::class
    ]
)

abstract class WalkHubDataBase : RoomDatabase() {
    abstract fun challengeDao(): ChallengeDao
    abstract fun userDao(): UserDao
    abstract fun noticeDao(): NoticeDao
    abstract fun badgeDao(): BadgeDao
    abstract fun rankDao(): RankDao
}