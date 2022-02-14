package com.semicolon.data.local.entity.notice

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.notice.NoticeEntity
import java.time.LocalDateTime


@Entity(tableName = "notice")
data class NoticeListRoomEntity(
    @PrimaryKey var noticeId: Int,
    val title: String,
    val createdAt: LocalDateTime,
    val noticeWriter: NoticeWriterRoomEntity
) {
    data class NoticeWriterRoomEntity(
        var writerId: Int,
        val writerName: String,
        val profileUrl: String
    )
}

fun NoticeListRoomEntity.toEntity() =
    NoticeEntity(
        noticeId = noticeId,
        title = title,
        createdAt = createdAt,
        noticeWriter = noticeWriter.toEntity()
    )

fun NoticeListRoomEntity.NoticeWriterRoomEntity.toEntity() =
    NoticeEntity.NoticeWriterEntity (
        writerId = writerId,
        writerName = writerName,
        profileUrl = profileUrl
    )

fun List<NoticeListRoomEntity>.toEntity() =
    map { it.toEntity() }

fun NoticeEntity.toDbEntity() =
    NoticeListRoomEntity(
        noticeId = noticeId,
        title = title,
        createdAt = createdAt,
        noticeWriter = noticeWriter.toDbEntity(),
    )

fun NoticeEntity.NoticeWriterEntity.toDbEntity() =
    NoticeListRoomEntity.NoticeWriterRoomEntity(
        writerId = writerId,
        writerName = writerName,
        profileUrl = profileUrl
    )

fun List<NoticeEntity>.toDbEntity() =
    map { it.toDbEntity() }