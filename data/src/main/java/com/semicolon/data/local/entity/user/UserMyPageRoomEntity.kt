package com.semicolon.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.users.UserMyPageEntity

@Entity(tableName = "userMyPage")
data class UserMyPageRoomEntity(
    val birthday: String,
    val classRoom: Int,
    val grade: Int,
    val height: Double,
    @PrimaryKey val id: Int = 0,
    val name: String,
    val profileImage: String,
    val schoolName: String,
    val sex: String,
    val titleBadge: TitleBadge,
    val weight: Int
) {
    data class TitleBadge(
        val id: Int,
        val image: String,
        val name: String
    )
}

fun UserMyPageRoomEntity.TitleBadge.toEntity() =
    UserMyPageEntity.TitleBadge(
        id = id,
        image = image,
        name = name
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
        id = id,
        image = image,
        name = name
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