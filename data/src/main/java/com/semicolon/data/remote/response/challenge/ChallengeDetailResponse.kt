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
    @SerializedName("user_scope") val userScope: String,
    @SerializedName("goal") val goal: Int,
    @SerializedName("goal_scope") val goalScope: String,
    @SerializedName("goal_type") val goalType: String,
    @SerializedName("award") val award: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("start_at") val startAt: String,
    @SerializedName("end_at") val endAt: String,
    @SerializedName("success_standard") val successStandard: Int,
    @SerializedName("value") val value: Int,
    @SerializedName("writer") val writer: ChallengeWriterResponse,
    @SerializedName("isMine") val isMine: Boolean,
    @SerializedName("is_participated") val isParticipated: Boolean,
    @SerializedName("participant_count") val participantCount: Int,
    @SerializedName("participant_list") val participantList: List<ParticipantList>
) {
    data class ChallengeWriterResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileUrl: String
    )
    data class ParticipantList(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileUrl: String
    )
}

fun ChallengeDetailResponse.toEntity() =
    ChallengeDetailEntity(
        name = name,
        content = content,
        userScope = userScope.toUserScope(),
        goal = goal,
        goalType = goalType.toGoalType(),
        goalScope = goalScope.toGoalScope(),
        award = award,
        imageUrl = imageUrl,
        startAt = startAt.toLocalDateTime(),
        endAt = endAt.toLocalDateTime(),
        successStandard = successStandard,
        value = value,
        writerEntity = writer.toEntity(),
        isMine = isMine,
        isParticipated = isParticipated,
        participantCount = participantCount,
        participantList = participantList.map { it.toEntity() }
    )


fun ChallengeDetailResponse.ChallengeWriterResponse.toEntity() =
    ChallengeDetailEntity.WriterEntity(
        id = id,
        name = name,
        profileImageUrl = profileUrl
    )

fun ChallengeDetailResponse.ParticipantList.toEntity() =
    ChallengeDetailEntity.ParticipantList(
        id = id,
        name = name,
        profileImageUrl = profileUrl
    )
