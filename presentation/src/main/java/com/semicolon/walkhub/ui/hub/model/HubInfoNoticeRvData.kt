package com.semicolon.walkhub.ui.hub.model

import com.semicolon.domain.entity.notice.NoticeEntity

data class HubInfoNoticeRvData(
    val title: String,
    val date: String
)

fun NoticeEntity.NoticeValueEntity.toRvData() =
    HubInfoNoticeRvData(
        title = content,
        date = createdAt.toString()
    )