package com.semicolon.domain.entity.notice

data class NoticeEntity (
    val noticeList: List<NoticeValueEntity>
){
    data class NoticeValueEntity (
        val noticeId: Int,
        val title: String,
        val createdAt: String,
        val noticeWriter: NoticeWriter
    )
}