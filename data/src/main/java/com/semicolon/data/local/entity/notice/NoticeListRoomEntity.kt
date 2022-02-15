package com.semicolon.data.local.entity.notice

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.notice.NoticeEntity
import java.time.LocalDateTime


@Entity(tableName = "notice")

data class NoticeListRoomEntity(
    val noticeValueRoomEntity: List<NoticeValueRoomEntity>
) {
    data class NoticeValueRoomEntity(
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
}

fun NoticeListRoomEntity.toEntity() =
    NoticeListRoomEntity(
        noticeValueRoomEntity = noticeValueRoomEntity
    )

fun NoticeListRoomEntity.NoticeValueRoomEntity.toEntity() =
    NoticeEntity.NoticeValueEntity(
        noticeId = noticeId,
        title = title,
        createdAt = createdAt,
        noticeWriter = noticeWriter.toEntity()
    )

fun NoticeListRoomEntity.NoticeValueRoomEntity.NoticeWriterRoomEntity.toEntity() =
    NoticeEntity.NoticeValueEntity.NoticeWriterEntity(
        writerId = writerId,
        writerName = writerName,
        profileUrl = profileUrl
    )

fun NoticeEntity.toDbEntity() =
    NoticeListRoomEntity(
        noticeValueRoomEntity = noticeValueEntity.map { it.toDbEntity() }
    )

fun NoticeEntity.NoticeValueEntity.toDbEntity() =
    NoticeListRoomEntity.NoticeValueRoomEntity(
        noticeId = noticeId,
        title = title,
        createdAt = createdAt,
        noticeWriter = noticeWriter.toDbEntity()
    )

fun NoticeEntity.NoticeValueEntity.NoticeWriterEntity.toDbEntity() =
    NoticeListRoomEntity.NoticeValueRoomEntity.NoticeWriterRoomEntity(
        writerId = writerId,
        writerName = writerName,
        profileUrl = profileUrl
    )