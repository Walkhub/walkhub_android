package com.semicolon.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.semicolon.data.local.converter.BadgeListTypeConverter
import com.semicolon.data.local.dao.ChallengeDao
import com.semicolon.data.local.dao.UserDao
import com.semicolon.data.local.entity.challenge.ChallengeRoomEntity
import com.semicolon.data.local.entity.user.UserMyPageRoomEntity
import com.semicolon.data.local.entity.user.UserOwnBadgeRoomEntity
import com.semicolon.data.local.entity.user.UserProfileRoomEntity

@Database(
    entities = [
        ChallengeRoomEntity::class,
        ChallengeRoomEntity::class,
        UserMyPageRoomEntity::class,
        UserProfileRoomEntity::class,
        UserOwnBadgeRoomEntity::class
    ], version = 1, exportSchema = false
)

@TypeConverters(value = [BadgeListTypeConverter::class])

abstract class WalkHubDataBase : RoomDatabase() {
    abstract fun challengeDao(): ChallengeDao
    abstract fun userDao(): UserDao
}