package com.semicolon.data.remote.response.notice.noticelist

import com.google.gson.annotations.SerializedName

data class NoticeListResponse(
    @SerializedName("notice_list") val noticeList: List<NoticeListValue>
)