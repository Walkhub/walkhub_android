package com.semicolon.domain.entity.notice

data class NoticeEntity(
    val noticeId: Int,
    val title: String,
    val createdAt: java.time.LocalDateTime,
    val noticeWriter: NoticeWriterEntity
){
    data class NoticeWriterEntity(
        val writerId: Int,
        val writerName: String,
        val profileUrl: String
    )
}