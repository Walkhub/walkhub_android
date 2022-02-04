package com.semicolon.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserProfileEntity

@Entity(tableName = "userProfile")
data class UserProfileRoomEntity(
    @PrimaryKey val id: Int,
    @SerializedName("class") val classRoom : Int,
    @SerializedName("grade") val grade: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("title_badge") val titleBadge: TitleBadge
) {
    data class TitleBadge(
        @SerializedName("id") val id: Int,
        @SerializedName("image") val image: String,
        @SerializedName("name") val name: String
    )
}

fun UserProfileRoomEntity.TitleBadge.toEntity() =
    UserProfileEntity.TitleBadge(
        id = id,
        image = image,
        name = name
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
        id = id,
        image = image,
        name = name
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