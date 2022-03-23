package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.challenge.ChallengeEntity
import com.semicolon.domain.enums.toGoalScope
import com.semicolon.domain.enums.toGoalType
import com.semicolon.domain.enums.toUserScope

data class ChallengeListResponse(
    @SerializedName("challenge_list") val challengeList: List<ChallengeResponse>
) {
    data class ChallengeResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("start_at") val startAt: String,
        @SerializedName("end_at") val endAt: String,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("user_scope") val userScope: String,
        @SerializedName("goal_scope") val goalScope: String,
        @SerializedName("goal_type") val goalType: String
    )

    fun ChallengeResponse.toEntity(): ChallengeEntity =
        ChallengeEntity(
            id = id,
            name = name,
            startAt = startAt.toLocalDateTime(),
            endAt = endAt.toLocalDateTime(),
            imageUrl = imageUrl,
            goalScope = goalScope.toGoalScope(),
            goalType = goalType.toGoalType(),
            userScope = userScope.toUserScope()
        )
}

fun ChallengeListResponse.toEntity(): List<ChallengeEntity> =
    challengeList.map { it.toEntity() }

