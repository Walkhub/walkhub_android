package com.semicolon.domain.entity.notice

import org.threeten.bp.LocalDateTime

data class NoticeEntity(
    val noticeValueEntity: List<NoticeValueEntity>
) {
    data class NoticeValueEntity(
        val id: Int,
        val title: String,
        val content: String,
        val scope: String,
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
