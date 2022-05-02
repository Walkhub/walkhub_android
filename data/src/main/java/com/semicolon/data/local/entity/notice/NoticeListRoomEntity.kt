package com.semicolon.data.local.entity.notice

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.data.util.toLocalDateTime
import com.semicolon.domain.entity.notice.NoticeEntity

@Entity(tableName = "noticeList")
data class NoticeListRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val noticeList: List<NoticeListValue>

) {
    data class NoticeListValue(
        val noticeId: Int,
        val title: String,
        val content: String,
        val scope: String,
        val createdAt: String,
        val noticeWriter: NoticeWriter
    ) {
        data class NoticeWriter(
            val writerId: Int,
            val writerName: String,
            val profileUrl: String
        )
    }


    fun NoticeListValue.toEntity() =
        NoticeEntity.NoticeValueEntity(
            id = id,
            title = title,
            content = content,
            scope = scope,
            createdAt = createdAt.toLocalDateTime(),
            noticeWriter = noticeWriter.toEntity()
        )

    fun NoticeListValue.NoticeWriter.toEntity() =
        NoticeEntity.NoticeValueEntity.NoticeWriterEntity(
            id = id,
            name = writerName,
            profileUrl = profileUrl
        )
}

fun NoticeListRoomEntity.toEntity() =
    NoticeEntity(
        noticeValueEntity = noticeList.map { it.toEntity() }
    )

fun NoticeEntity.toDbEntity() =
    NoticeListRoomEntity(
        noticeList = noticeValueEntity.map { it.toDbEntity() }
    )

fun NoticeEntity.NoticeValueEntity.toDbEntity() =
    NoticeListRoomEntity.NoticeListValue(
        noticeId = id,
        title = title,
        content = content,
        scope = scope,
        createdAt = createdAt.toString(),
        noticeWriter = noticeWriter.toDbEntity()
    )

fun NoticeEntity.NoticeValueEntity.NoticeWriterEntity.toDbEntity() =
    NoticeListRoomEntity.NoticeListValue.NoticeWriter(
        writerId = id,
        writerName = name,
        profileUrl = profileUrl
    )
