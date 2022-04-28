package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.MyChallengeEntity
import com.semicolon.domain.enums.toGoalScope
import com.semicolon.domain.enums.toGoalType

data class MyChallengeResponse(
    @SerializedName("challenge_list") val challengeList: List<ChallengeResponse>
) {
    data class ChallengeResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("start_at") val startAt: String,
        @SerializedName("end_at") val endAt: String,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("goal") val goal: Int,
        @SerializedName("goal_scope") val goalScope: String,
        @SerializedName("goal_type") val goalType: String,
        @SerializedName("writer") val writer: User,
        @SerializedName("total_walk_count") val totalWalkCount: Int
    )

    data class User(
        @SerializedName("user_id") val userId: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_image_url") val profileImageUrl: String
    )

    fun ChallengeResponse.toEntity() =
        MyChallengeEntity(
            id = id,
            name = name,
            startAt = startAt.toLocalDateTime(),
            endAt = endAt.toLocalDateTime(),
            imageUrl = imageUrl,
            goal = goal,
            goalScope = goalScope.toGoalScope(),
            goalType = goalType.toGoalType(),
            writer = writer.toWriterEntity(),
            totalWalkCount = totalWalkCount
        )

    private fun User.toWriterEntity(): MyChallengeEntity.Writer =
        MyChallengeEntity.Writer(
            id = userId,
            name = name,
            profileImageUrl = profileImageUrl
        )
}
fun MyChallengeResponse.toEntity(): List<MyChallengeEntity> =
    challengeList.map { it.toEntity() }