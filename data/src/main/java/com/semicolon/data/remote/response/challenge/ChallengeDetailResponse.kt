package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.enums.toGoalScope
import com.semicolon.domain.enums.toGoalType
import com.semicolon.domain.enums.toUserScope

data class ChallengeDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("content") val content: String,
    @SerializedName("award") val award: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("start_at") val startAt: String,
    @SerializedName("end_at") val endAt: String,
    @SerializedName("goal") val goal: Int,
    @SerializedName("goal_scope") val goalScope: String,
    @SerializedName("goal_type") val goalType: String,
    @SerializedName("user_scope") val userScope: String,
    @SerializedName("isMine") val isMine: Boolean,
    @SerializedName("participant_count") val participantCount: Int,
    @SerializedName("writer") val writer: ChallengeWriterResponse
) {
    data class ChallengeWriterResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileUrl: String
    )
}

fun ChallengeDetailResponse.toEntity() =
    ChallengeDetailEntity(
        name = name,
        content = content,
        goal = goal,
        goalType = goalType.toGoalType(),
        goalScope = goalScope.toGoalScope(),
        userScope = userScope.toUserScope(),
        award = award,
        imageUrl = imageUrl,
        startAt = startAt.toLocalDateTime(),
        endAt = endAt.toLocalDateTime(),
        isMine = isMine,
        participantCount = participantCount,
        writerEntity = writer.toEntity()
    )

fun ChallengeDetailResponse.ChallengeWriterResponse.toEntity() =
    ChallengeDetailEntity.WriterEntity(
        id = id,
        name = name,
        profileImageUrl = profileUrl
    )