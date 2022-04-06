package com.semicolon.data.local.entity.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity

@Entity(tableName = "userProfile")
data class UserProfileRoomEntity(
    @PrimaryKey val userId: Int,
    val name: String,
    val profileImageUrl: String,
    val schoolName: String,
    val schoolImageUrl: String,
    val grade: Int,
    val classNum: Int,
    val dailyWalkCountGoal: Int,
    @Embedded val titleBadge: TitleBadge,
    @Embedded val level: Level
) {
    data class TitleBadge(
        val badgeId: Int,
        val badgeName: String,
        val badgeImageUrl: String
    )

    data class Level(
        val levelName: String,
        val levelImageUrl: String
    )

    fun TitleBadge.toEntity() =
        UserProfileEntity.TitleBadge(
            badgeId = badgeId,
            badgeName = badgeName,
            badgeImageUrl = badgeImageUrl
        )

    fun Level.toEntity() =
        UserProfileEntity.Level(
            levelName = levelName,
            levelImageUrl = levelImageUrl
        )
}

fun UserProfileRoomEntity.toEntity() =
    UserProfileEntity(
        userId = userId,
        name = name,
        profileImageUrl = profileImageUrl,
        schoolName = schoolName,
        schoolImageUrl = schoolImageUrl,
        grade = grade,
        classNum = classNum,
        dailyWalkCountGoal = dailyWalkCountGoal,
        titleBadge = titleBadge.toEntity(),
        level = level.toEntity()
    )

fun UserProfileEntity.TitleBadge.toDbEntity() =
    UserProfileRoomEntity.TitleBadge(
        badgeId = badgeId,
        badgeName = badgeName,
        badgeImageUrl = badgeImageUrl
    )

fun UserProfileEntity.Level.toDbEntity() =
    UserProfileRoomEntity.Level(
        levelName = levelName,
        levelImageUrl = levelImageUrl
    )

fun UserProfileEntity.toDbEntity() =
    UserProfileRoomEntity(
        userId = userId,
        name = name,
        profileImageUrl = profileImageUrl,
        schoolName = schoolName,
        schoolImageUrl = schoolImageUrl,
        grade = grade,
        classNum = classNum,
        dailyWalkCountGoal = dailyWalkCountGoal,
        titleBadge = titleBadge.toDbEntity(),
        level = level.toDbEntity()
    )