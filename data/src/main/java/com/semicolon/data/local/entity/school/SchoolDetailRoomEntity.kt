package com.semicolon.data.local.entity.school

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.school.SchoolDetailEntity

@Entity(tableName = "schoolDetail")
data class SchoolDetailRoomEntity(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    @Embedded(prefix = "week") val week: Week,
    @Embedded(prefix = "month") val month: Month
) {
    data class Week(
        val totalWalkCount: Int?,
        val date: String?,
        val totalUserCount: Int?,
        val ranking: Int?
    )

    data class Month(
        val totalWalkCount: Int?,
        val date: String?,
        val totalUserCount: Int?,
        val ranking: Int?
    )
}

fun SchoolDetailEntity.Week.toDbEntity() =
    SchoolDetailRoomEntity.Week(
        totalWalkCount = totalWalkCount,
        date = date,
        totalUserCount = totalUserCount,
        ranking = ranking
    )

fun SchoolDetailEntity.Month.toDbEntity() =
    SchoolDetailRoomEntity.Month(
        totalWalkCount = totalWalkCount,
        date = date,
        totalUserCount = totalUserCount,
        ranking = ranking
    )

fun SchoolDetailEntity.toDbEntity() =
    SchoolDetailRoomEntity(
        week = week.toDbEntity(),
        month = month.toDbEntity()
    )

fun SchoolDetailRoomEntity.Week.toEntity() =
    SchoolDetailEntity.Week(
        totalWalkCount = totalWalkCount,
        date = date,
        totalUserCount = totalUserCount,
        ranking = ranking
    )

fun SchoolDetailRoomEntity.Month.toEntity() =
    SchoolDetailEntity.Month(
        totalWalkCount = totalWalkCount,
        date = date,
        totalUserCount = totalUserCount,
        ranking = ranking
    )

fun SchoolDetailRoomEntity.toEntity() =
    SchoolDetailEntity(
        week = week.toEntity(),
        month = month.toEntity()
    )
