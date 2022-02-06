package com.semicolon.domain.entity.notice

import org.threeten.bp.LocalDateTime

data class NoticeEntity(
    val noticeId: Int,
    val title: String,
    val createdAt: LocalDateTime,
    val noticeWriter: NoticeWriterEntity
) {
    data class NoticeWriterEntity(
        val writerId: Int,
        val writerName: String,
        val profileUrl: String
    )
}