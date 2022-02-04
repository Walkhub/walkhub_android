package com.semicolon.data.local.entity.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserProfileEntity

@Entity(tableName = "userProfile")
data class UserProfileRoomEntity(
    @PrimaryKey val id: Int,
    val classRoom : Int,
    val grade: Int,
    val name: String,
    val profileImage: String,
    val schoolName: String,
    @Embedded val titleBadge: TitleBadge
) {
    data class TitleBadge(
        val badgeId: Int,
        val badgeImage: String,
        val badgeName: String
    )
}

fun UserProfileRoomEntity.TitleBadge.toEntity() =
    UserProfileEntity.TitleBadge(
        badgeId = badgeId,
        badgeImage = badgeImage,
        badgeName = badgeName
    )

fun UserProfileRoomEntity.toEntity() =
    UserProfileEntity(
        classRoom = classRoom,
        grade = grade,
        name = name,
        profileImage = profileImage,
        schoolName = schoolName,
        titleBadge = titleBadge.toEntity()
    )

fun UserProfileEntity.TitleBadge.toDbEntity() =
    UserProfileRoomEntity.TitleBadge(
        badgeId = badgeId,
        badgeImage = badgeImage,
        badgeName = badgeName
    )

fun UserProfileEntity.toDbEntity(id: Int) =
    UserProfileRoomEntity(
        id = id,
        classRoom = classRoom,
        grade = grade,
        name = name,
        profileImage = profileImage,
        schoolName = schoolName,
        titleBadge = titleBadge.toDbEntity()
    )