package com.semicolon.domain.entity.notice

data class NoticeDetailEntity(
    val title: String,
    val content: String,
    val createdAt: String,
    val noticeWriter: NoticeWriterEntity
)