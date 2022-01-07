package com.semicolon.data.remote.response.notice

import com.google.gson.annotations.SerializedName

data class NoticeWriter (
    @SerializedName("id") val writerId: Long,
    @SerializedName("name") val writerName: String,
    @SerializedName("profile_url") val profileUrl: String
)