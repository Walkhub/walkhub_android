package com.semicolon.data.remote.response.notice

import com.google.gson.annotations.SerializedName
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.notice.NoticeEntity

data class NoticeListResponse(
    @SerializedName("notice_list") val noticeList: List<NoticeListValue>

) {
    data class NoticeListValue(
        @SerializedName("id") val noticeId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String,
        @SerializedName("scope") val scope: String,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("writer") val noticeWriter: NoticeWriter
    ) {
        data class NoticeWriter(
            @SerializedName("id") val writerId: Int,
            @SerializedName("name") val writerName: String,
            @SerializedName("profile_image_url") val profileUrl: String
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
        title = title,
        content = content,
        scope = scope,
        createdAt = createdAt.toLocalDateTime(),
        noticeWriter = noticeWriter.toEntity()
    )

fun NoticeListResponse.toEntity() =
    NoticeEntity(
        noticeValueEntity = noticeList.map { it.toEntity() }
    )