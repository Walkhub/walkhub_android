package com.semicolon.domain.entity.notice

data class NoticeEntity (
    val noticeId: Int,
    val title: String,
    val createdAt: String,
    val noticeWriter: NoticeWriterEntity
)