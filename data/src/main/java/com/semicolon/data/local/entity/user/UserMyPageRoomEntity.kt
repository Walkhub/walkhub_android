package com.semicolon.data.local.entity.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.data.remote.response.users.FetchUserProfileResponse
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity

@Entity(tableName = "userMyPage")
data class UserMyPageRoomEntity(
    @PrimaryKey val userId: Int,
    val name: String,
    val profileImageUrl: String,
    val schoolName: String,
    val grade: Int,
    val classNum: Int,
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
        UserMyPageEntity.TitleBadge(
            badgeId = badgeId,
            badgeName = badgeName,
            badgeImageUrl = badgeImageUrl
        )

    fun Level.toEntity() =
        UserMyPageEntity.Level(
            levelName = levelName,
            levelImageUrl = levelImageUrl
        )
}

fun UserMyPageRoomEntity.toEntity() =
    UserMyPageEntity(
        userId = userId,
        name = name,
        profileImageUrl = profileImageUrl,
        schoolName = schoolName,
        grade = grade,
        classNum = classNum,
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
        levelName = levelName,
        levelImageUrl = levelImageUrl
    )

fun UserMyPageEntity.toDbEntity() =
    UserMyPageRoomEntity(
        userId = userId,
        name = name,
        profileImageUrl = profileImageUrl,
        schoolName = schoolName,
        grade = grade,
        classNum = classNum,
        titleBadge = titleBadge.toDbEntity(),
        level = level.toDbEntity()
    )