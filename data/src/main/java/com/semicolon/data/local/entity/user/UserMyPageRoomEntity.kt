package com.semicolon.data.local.entity.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.users.UserMyPageEntity

@Entity(tableName = "userMyPage")
data class UserMyPageRoomEntity(
    @PrimaryKey val userId: Int,
    val name: String,
    val profileImageUrl: String,
    val schoolId: Int,
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
        val levelId: Int,
        val levelName: String,
        val levelImageUrl: String
    )

    fun TitleBadge.toEntity() =
        UserMyPageEntity.TitleBadge(
            badgeId = badgeId,
            badgeName = badgeName,
            badgeImageUrl = badgeImageUrl
        )

    fun Level.toEntity() =
        UserMyPageEntity.Level(
            levelId = levelId,
            levelName = levelName,
            levelImageUrl = levelImageUrl
        )
}

fun UserMyPageRoomEntity.toEntity() =
    UserMyPageEntity(
        userId = userId,
        name = name,
        profileImageUrl = profileImageUrl,
        schoolId = schoolId,
        schoolName = schoolName,
        schoolImageUrl = schoolImageUrl,
        grade = grade,
        classNum = classNum,
        dailyWalkCountGoal = dailyWalkCountGoal,
        titleBadge = titleBadge.toEntity(),
        level = level.toEntity()
    )

fun UserMyPageEntity.TitleBadge.toDbEntity() =
    UserMyPageRoomEntity.TitleBadge(
        badgeId = badgeId,
        badgeName = badgeName,
        badgeImageUrl = badgeImageUrl
    )

fun UserMyPageEntity.Level.toDbEntity() =
    UserMyPageRoomEntity.Level(
        levelId = levelId,
        levelName = levelName,
        levelImageUrl = levelImageUrl
    )

fun UserMyPageEntity.toDbEntity() =
    UserMyPageRoomEntity(
        userId = userId,
        name = name,
        profileImageUrl = profileImageUrl,
        schoolId = schoolId,
        schoolName = schoolName,
        schoolImageUrl = schoolImageUrl,
        grade = grade,
        classNum = classNum,
        dailyWalkCountGoal = dailyWalkCountGoal,
        titleBadge = titleBadge.toDbEntity(),
        level = level.toDbEntity()
    )