package com.semicolon.data.remote.response.notice

import com.google.gson.annotations.SerializedName

data class NoticeDetailResponse(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("isMine") val isMine: Boolean,
    @SerializedName("writer") val noticeWriter: NoticeWriter
)
