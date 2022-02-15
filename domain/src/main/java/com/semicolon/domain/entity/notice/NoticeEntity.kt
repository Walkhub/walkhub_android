package com.semicolon.domain.entity.notice

import java.time.LocalDateTime

data class NoticeEntity(
    val noticeValueEntity: List<NoticeValueEntity>
) {
    data class NoticeValueEntity(
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
}