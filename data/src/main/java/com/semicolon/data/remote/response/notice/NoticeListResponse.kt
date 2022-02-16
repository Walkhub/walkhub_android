package com.semicolon.data.remote.response.notice

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.notice.NoticeEntity
import java.time.LocalDateTime

data class NoticeListResponse(
    @SerializedName("notice_list") val noticeList: List<NoticeListValue>

) {
    data class NoticeListValue(
        @SerializedName("id") val noticeId: Int,
        @SerializedName("title") val content: String,
        @SerializedName("created_at") val createdAt: LocalDateTime,
        @SerializedName("writer") val noticeWriter: NoticeWriter
    ) {
        data class NoticeWriter(
            @SerializedName("id") val writerId: Int,
            @SerializedName("name") val writerName: String,
            @SerializedName("profile_url") val profileUrl: String
        )
    }
}

fun NoticeListResponse.NoticeListValue.NoticeWriter.toEntity() =
    NoticeEntity.NoticeValueEntity.NoticeWriterEntity(
        id = writerId,
        name = writerName,
        profileUrl = profileUrl
    )

fun NoticeListResponse.NoticeListValue.toEntity() =
    NoticeEntity.NoticeValueEntity(
        id = noticeId,
        content = content,
        createdAt = createdAt,
        noticeWriter = noticeWriter.toEntity()
    )

fun NoticeListResponse.toEntity() =
    NoticeEntity(
        noticeValueEntity = noticeList.map { it.toEntity() }
    )