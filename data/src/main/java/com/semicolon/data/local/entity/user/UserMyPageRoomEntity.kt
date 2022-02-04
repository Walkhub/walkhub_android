package com.semicolon.data.local.entity.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.users.UserMyPageEntity

@Entity(tableName = "userMyPage")
data class UserMyPageRoomEntity(
    val birthday: String,
    val classRoom: Int,
    val grade: Int,
    val height: Double,
    @PrimaryKey val id: Int,
    val name: String,
    val profileImage: String,
    val schoolName: String,
    val sex: String,
    @Embedded val titleBadge: TitleBadge,
    val weight: Int
) {
    data class TitleBadge(
        val badgeId: Int,
        val badgeImage: String,
        val badgeName: String
    )
}

fun UserMyPageRoomEntity.TitleBadge.toEntity() =
    UserMyPageEntity.TitleBadge(
        badgeId = badgeId,
        badgeImage = badgeImage,
        badgeName = badgeName
    )

fun UserMyPageRoomEntity.toEntity() =
    UserMyPageEntity(
        birthday = birthday,
        classRoom = classRoom,
        grade = grade,
        height = height,
        id = id,
        name = name,
        profileImage = profileImage,
        schoolName = schoolName,
        sex = sex,
        titleBadge = titleBadge.toEntity(),
        weight = weight
    )

fun UserMyPageEntity.TitleBadge.toDbEntity() =
    UserMyPageRoomEntity.TitleBadge(
        badgeId = badgeId,
        badgeImage = badgeImage,
        badgeName = badgeName
    )

fun UserMyPageEntity.toDbEntity() =
    UserMyPageRoomEntity(
        birthday = birthday,
        classRoom = classRoom,
        grade = grade,
        height = height,
        id = id,
        name = name,
        profileImage = profileImage,
        schoolName = schoolName,
        sex = sex,
        titleBadge = titleBadge.toDbEntity(),
        weight = weight
    )