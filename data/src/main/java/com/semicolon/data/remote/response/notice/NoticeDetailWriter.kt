package com.semicolon.data.remote.response.notice

import com.google.gson.annotations.SerializedName

data class NoticeDetailWriter(
    @SerializedName("id") val writerId: Int,
    @SerializedName("name") val writerName: String,
    @SerializedName("profile_url") val profileUrl: String
)
