package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.enum.toChallengeScope
import java.time.LocalDateTime

data class ChallengeDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("content") val content: String,
    @SerializedName("goal") val goal: Int,
    @SerializedName("award") val award: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("start_at") val startAt: LocalDateTime,
    @SerializedName("end_at") val endAt: LocalDateTime,
    @SerializedName("scope") val scope: String,
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
        award = award,
        imageUrl = imageUrl,
        startAt = startAt,
        endAt = endAt,
        scope = scope.toChallengeScope(),
        participantCount = participantCount,
        writerEntity = writer.toEntity()
    )

fun ChallengeDetailResponse.ChallengeWriterResponse.toEntity() =
    ChallengeDetailEntity.WriterEntity(
        id = id,
        name = name,
        profileImageUrl = profileUrl
    )