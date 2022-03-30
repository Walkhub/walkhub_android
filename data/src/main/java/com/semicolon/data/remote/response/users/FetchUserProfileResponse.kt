package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity

data class FetchUserProfileResponse(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("school_image_url") val schoolImageUrl: String,
    @SerializedName("grade") val grade: Int,
    @SerializedName("class_num") val classNum: Int,
    @SerializedName("daily_walk_count_goal") val dailyWalkCountGoal: Int,
    @SerializedName("title_badge") val titleBadge: TitleBadge,
    @SerializedName("level") val level: Level
) {

    data class TitleBadge(
        @SerializedName("badge_id") val badgeId: Int,
        @SerializedName("name") val badgeName: String,
        @SerializedName("image_url") val badgeImageUrl: String
    )

    data class Level(
        @SerializedName("name") val levelName: String,
        @SerializedName("image_url") val levelImageUrl: String
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

fun FetchUserProfileResponse.toEntity() =
    UserProfileEntity(
        userId = userId,
        name = name,
        profileImageUrl = profileImageUrl,
        schoolName = schoolName,
        schoolImageUrl = schoolImageUrl,
        grade = grade,
        classNum = classNum,
        titleBadge = titleBadge.toEntity(),
        dailyWalkCountGoal = dailyWalkCountGoal,
        level = level.toEntity()
    )
