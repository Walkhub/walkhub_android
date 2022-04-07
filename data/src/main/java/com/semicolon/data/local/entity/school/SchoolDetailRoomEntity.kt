package com.semicolon.data.local.entity.school

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.school.SchoolDetailEntity

@Entity(tableName = "schoolDetail")
data class SchoolDetailRoomEntity(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    val totalUserCount: Int,
    val weekTotalWalkCount: Int,
    val monthTotalWalkCount: Int,
    val weekRanking: Int,
    val monthRanking: Int
)

fun SchoolDetailEntity.toDbEntity() =
    SchoolDetailRoomEntity(
        totalUserCount = totalUserCount,
        weekTotalWalkCount = weekTotalWalkCount,
        monthTotalWalkCount = monthTotalWalkCount,
        weekRanking = weekRanking,
        monthRanking = monthRanking
    )

fun SchoolDetailRoomEntity.toEntity() =
    SchoolDetailEntity(
        totalUserCount = totalUserCount,
        weekTotalWalkCount = weekTotalWalkCount,
        monthTotalWalkCount = monthTotalWalkCount,
        weekRanking = weekRanking,
        monthRanking = monthRanking
    )
