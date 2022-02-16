package com.semicolon.domain.entity.notice

import java.time.LocalDateTime

data class NoticeEntity (
    val noticeId: Int,
    val title: String,
    val createdAt: LocalDateTime,
    val noticeWriter: NoticeWriterEntity
)