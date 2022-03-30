package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.entity.challenge.ChallengeParticipantEntity
import com.semicolon.domain.enums.toGoalScope
import com.semicolon.domain.enums.toGoalType

data class ChallengeListResponse(
    @SerializedName("challenge_list") val challengeList: List<ChallengeResponse>
) {
    data class ChallengeResponse(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("start_at") val startAt: String,
        @SerializedName("end_at") val endAt: String,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("goal") val goal: Int,
        @SerializedName("goal_type") val goalType: String,
        @SerializedName("goal_scope") val goalScope: String,
        @SerializedName("writer") val writer: User,
        @SerializedName("award") val award: String,
        @SerializedName("participant_count") val participantCount: Int,
        @SerializedName("participant_list") val participantList: List<User>
    )

    data class User(
        @SerializedName("user_id") val userId: Long,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String
    )


    fun ChallengeResponse.toEntity(): ChallengeEntity =
        ChallengeEntity(
            id = id,
            name = name,
            startAt = startAt.toLocalDateTime(),
            endAt = endAt.toLocalDateTime(),
            imageUrl = imageUrl,
            goal = goal,
            goalScope = goalScope.toGoalScope(),
            goalType = goalType.toGoalType(),
            writer = writer.toWriterEntity(),
            award = award,
            participantCount = participantCount,
            participantList = participantList.map { it.toParticipantEntity() }
        )

    private fun User.toWriterEntity(): ChallengeEntity.Writer =
        ChallengeEntity.Writer(
            userId = userId,
            name = name,
            profileImageUrl = profileImageUrl
        )

    private fun User.toParticipantEntity(): ChallengeParticipantEntity =
        ChallengeParticipantEntity(
            id = userId,
            name = name,
            profileImageUrl = profileImageUrl
        )
}

fun ChallengeListResponse.toEntity(): List<ChallengeEntity> =
    challengeList.map { it.toEntity() }

