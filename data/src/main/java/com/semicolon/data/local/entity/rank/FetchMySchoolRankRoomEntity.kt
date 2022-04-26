package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.FetchMySchoolRankEntity

@Entity(tableName = "mySchoolRank")
data class FetchMySchoolRankRoomEntity(
    @PrimaryKey val schoolId: Int,
    val name: String,
    val logoImageUrl: String,
    val grade: Int?,
    val classNum: Int?
)

fun FetchMySchoolRankRoomEntity.toEntity() =
    FetchMySchoolRankEntity(
        schoolId = schoolId,
        name = name,
        logoImageUrl = logoImageUrl,
        grade = grade,
        classNum = classNum
    )


fun FetchMySchoolRankEntity.toDbEntity() =
    FetchMySchoolRankRoomEntity(
        schoolId = schoolId,
        name = name,
        logoImageUrl = logoImageUrl,
        grade = grade,
        classNum = classNum
    )
