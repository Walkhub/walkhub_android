package com.semicolon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.semicolon.data.local.converter.BadgeListTypeConverter
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.dao.NotificationDao
import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.entity.challenge.ChallengeDetailRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeParticipantRoomEntity
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity
import com.semicolon.data.local.entity.user.UserMyPageRoomEntity
import com.semicolon.data.local.entity.user.UserOwnBadgeRoomEntity
import com.semicolon.data.local.entity.user.UserProfileRoomEntity
import com.semicolon.data.local.entity.notification.NotificationRoomEntity

@Database(
    entities = [
        ChallengeRoomEntity::class,
        ChallengeDetailRoomEntity::class,
        ChallengeParticipantRoomEntity::class,
        UserMyPageRoomEntity::class,
        UserProfileRoomEntity::class,
        UserOwnBadgeRoomEntity::class,
        NotificationRoomEntity::class
    ], version = 1, exportSchema = false
)

@TypeConverters(value = [BadgeListTypeConverter::class])

abstract class WalkHubDataBase : RoomDatabase() {
    abstract fun challengeDao(): ChallengeDao
    abstract fun userDao(): UserDao
    abstract fun notification(): NotificationDao
}