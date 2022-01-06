package com.semicolon.data.remote.response.notice.noticelist

import com.google.gson.annotations.SerializedName

data class NoticeListValue (
    @SerializedName("id") val noticeId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("writer") val noticeWriter: NoticeWriter
)