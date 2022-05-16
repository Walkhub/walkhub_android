package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.UserMyPageEntity

data class FetchMyPageResponse(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image_url") val profileImageUrl: String,
    @SerializedName("school_id") val schoolId: Long,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("school_image_url") val schoolImageUrl: String,
    @SerializedName("grade") val grade: Int,
    @SerializedName("class_num") val classNum: Int,
    @SerializedName("daily_walk_count_goal") val dailyWalkCountGoal : Int,
    @SerializedName("title_badge") val titleBadge: TitleBadge,
    @SerializedName("level") val level: Level
) {
    data class TitleBadge(
        @SerializedName("badge_id") val badgeId: Int,
        @SerializedName("name") val badgeName: String,
        @SerializedName("image_url") val badgeImageUrl: String
    )

    data class Level(
        @SerializedName("level_id") val levelId: Int,
        @SerializedName("name") val levelName: String,
        @SerializedName("image_url") val levelImageUrl: String
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


fun FetchMyPageResponse.toEntity() =
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
