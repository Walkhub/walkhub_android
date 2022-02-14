package com.semicolon.data.remote.response.notice

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class NoticeListResponse(
    @SerializedName("notice_list") val noticeList: List<NoticeListValue>

){
    data class NoticeListValue (
        @SerializedName("id") val noticeId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("created_at") val createdAt: LocalDateTime,
        @SerializedName("writer") val noticeWriter: NoticeWriter
    ){
        data class NoticeWriter (
            @SerializedName("id") val writerId: Int,
            @SerializedName("name") val writerName: String,
            @SerializedName("profile_url") val profileUrl: String
        )
    }
}