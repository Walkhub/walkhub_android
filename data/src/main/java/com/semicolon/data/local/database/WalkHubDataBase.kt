package com.semicolon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
<<<<<<< HEAD
import com.semicolon.data.local.converter.BadgeListTypeConverter
=======
import com.semicolon.data.local.converter.CaloriesListTypeConverter
import com.semicolon.data.local.converter.MyBadgeListTypeConverter
import com.semicolon.data.local.converter.NewBadgeListTypeConverter
import com.semicolon.data.local.converter.UserBadgeListTypeConverter
import com.semicolon.data.local.dao.BadgeDao
>>>>>>> 60_Notice_data
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.dao.NoticeDao
import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.entity.badge.FetchMyBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchNewBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchUserBadgesRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeDetailRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeParticipantRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
<<<<<<< HEAD
=======
import com.semicolon.data.local.entity.user.FetchCaloriesLevelRoomEntity
>>>>>>> 60_Notice_data
import com.semicolon.data.local.entity.user.UserMyPageRoomEntity
import com.semicolon.data.local.entity.user.UserOwnBadgeRoomEntity
import com.semicolon.data.local.entity.user.UserProfileRoomEntity

@Database(
    entities = [
        ChallengeRoomEntity::class,
        ChallengeDetailRoomEntity::class,
        ChallengeParticipantRoomEntity::class,
        UserMyPageRoomEntity::class,
        UserProfileRoomEntity::class,
        UserOwnBadgeRoomEntity::class,
<<<<<<< HEAD
        NoticeListRoomEntity::class
    ], version = 1, exportSchema = false
)

@TypeConverters(value = [BadgeListTypeConverter::class])
=======
        NoticeListRoomEntity::class,
        FetchCaloriesLevelRoomEntity::class,
        FetchMyBadgesRoomEntity::class,
        FetchNewBadgesRoomEntity::class,
        FetchUserBadgesRoomEntity::class
    ], version = 1, exportSchema = false
)

@TypeConverters(
    value = [
        CaloriesListTypeConverter::class,
        MyBadgeListTypeConverter::class,
        NewBadgeListTypeConverter::class,
        UserBadgeListTypeConverter::class
    ]
)
>>>>>>> 60_Notice_data

abstract class WalkHubDataBase : RoomDatabase() {
    abstract fun challengeDao(): ChallengeDao
    abstract fun userDao(): UserDao
    abstract fun noticeDao(): NoticeDao
    abstract fun badgeDao(): BadgeDao
}