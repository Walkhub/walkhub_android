package com.semicolon.data.remote.response.notice.noticelist

import com.google.gson.annotations.SerializedName

data class NoticeWriter (
    @SerializedName("id") val writerId: Int,
    @SerializedName("name") val writerName: String,
    @SerializedName("profile_url") val profileUrl: String
)