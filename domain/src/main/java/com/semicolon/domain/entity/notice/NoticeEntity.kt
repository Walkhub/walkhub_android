package com.semicolon.domain.entity.notice

import java.time.LocalDateTime

data class NoticeEntity (
    val noticeId: Int,
    val title: String,
    val createdAt: LocalDateTime,
    val noticeWriter: NoticeWriterEntity
)

data class NoticeEntity(
    val noticeValueEntity: List<NoticeValueEntity>
) {
    data class NoticeValueEntity(
        val id: Int,
        val content: String,
        val createdAt: LocalDateTime,
        val noticeWriter: NoticeWriterEntity
    ) {
        data class NoticeWriterEntity(
            val id: Int,
            val name: String,
            val profileUrl: String
        )
    }
}
