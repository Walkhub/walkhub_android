package com.semicolon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.semicolon.data.local.converter.*
import com.semicolon.data.local.dao.*
import com.semicolon.data.local.converter.NoticeListTypeConverter
import com.semicolon.data.local.converter.CaloriesListTypeConverter
import com.semicolon.data.local.converter.MyBadgeListTypeConverter
import com.semicolon.data.local.converter.NewBadgeListTypeConverter
import com.semicolon.data.local.converter.UserBadgeListTypeConverter
import com.semicolon.data.local.dao.BadgeDao
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.dao.NotificationDao
import com.semicolon.data.local.dao.NoticeDao
import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.entity.badge.FetchMyBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchNewBadgesRoomEntity
import com.semicolon.data.local.entity.badge.FetchUserBadgesRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeDetailRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeParticipantRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity
import com.semicolon.data.local.entity.exercise.LocationRecordRoomEntity
import com.semicolon.data.local.entity.level.LevelRoomEntity
import com.semicolon.data.local.entity.notice.NoticeListRoomEntity
import com.semicolon.data.local.entity.rank.*
import com.semicolon.data.local.entity.notification.NotificationRoomEntity
import com.semicolon.data.local.entity.notification.NotificationStatusRoomEntity
import com.semicolon.data.local.entity.school.SchoolDetailRoomEntity
import com.semicolon.data.local.entity.user.*

@Database(
    entities = [
        ChallengeRoomEntity::class,
        ChallengeDetailRoomEntity::class,
        ChallengeParticipantRoomEntity::class,
        UserMyPageRoomEntity::class,
        UserProfileRoomEntity::class,
        NotificationRoomEntity::class,
        FetchCaloriesLevelRoomEntity::class,
        FetchMyBadgesRoomEntity::class,
        FetchNewBadgesRoomEntity::class,
        FetchUserBadgesRoomEntity::class,
        NoticeListRoomEntity::class,
        NotificationStatusRoomEntity::class,
        OurSchoolUserRankRoomEntity::class,
        SchoolRankAndSearchRoomEntity::class,
        SearchUserRoomEntity::class,
        UserRankRoomEntity::class,
        LocationRecordRoomEntity::class,
        LevelRoomEntity::class,
        FetchAuthInfoRoomEntity::class,
        FetchDailyGoalRoomEntity::class,
        FetchInfoRoomEntity::class,
        FetchUserHealthRoomEntity::class,
        SchoolDetailRoomEntity::class,
        FetchMySchoolRankRoomEntity::class
    ], version = 1, exportSchema = false
)

@TypeConverters(
    value = [
        NoticeListTypeConverter::class,
        CaloriesListTypeConverter::class,
        MyBadgeListTypeConverter::class,
        NewBadgeListTypeConverter::class,
        UserBadgeListTypeConverter::class,
        RankOurSchoolTypeConverter::class,
        RankSchoolRankAndSearchTypeConverter::class,
        RankSearchUserTypeConverter::class,
        RankUserRankTypeConverter::class,
        ChallengeParticipantTypeConverter::class,
        NotificationTypeConverter::class,
        NotificationStatusListTypeConverter::class
    ]

)

abstract class WalkHubDataBase : RoomDatabase() {
    abstract fun challengeDao(): ChallengeDao
    abstract fun userDao(): UserDao
    abstract fun notificationDao(): NotificationDao
    abstract fun noticeDao(): NoticeDao
    abstract fun badgeDao(): BadgeDao
    abstract fun rankDao(): RankDao
    abstract fun locationRecordDao(): LocationRecordDao
    abstract fun levelDao(): LevelDao
    abstract fun schoolDao(): SchoolDao
}