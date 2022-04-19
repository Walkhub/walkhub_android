package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.FetchMySchoolRankEntity

@Entity(tableName = "mySchoolRank")
data class FetchMySchoolRankRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val mySchoolRank: MySchoolRank
) {
    data class MySchoolRank(
        val schoolId: Int,
        val name: String,
        val logoImageUrl: String,
        val grade: Int?,
        val classNum: Int?
    )
}

fun FetchMySchoolRankRoomEntity.MySchoolRank.toEntity() =
    FetchMySchoolRankEntity.MySchoolRank(
        schoolId = schoolId,
        name = name,
        logoImageUrl = logoImageUrl,
        grade = grade,
        classNum = classNum
    )

fun FetchMySchoolRankRoomEntity.toEntity() =
    FetchMySchoolRankEntity(
        mySchoolRank = mySchoolRank.toEntity()
    )

fun FetchMySchoolRankEntity.MySchoolRank.toDbEntity() =
    FetchMySchoolRankRoomEntity.MySchoolRank(
        schoolId = schoolId,
        name = name,
        logoImageUrl = logoImageUrl,
        grade = grade,
        classNum = classNum
    )

fun FetchMySchoolRankEntity.toDbEntity() =
    FetchMySchoolRankRoomEntity(
        mySchoolRank = mySchoolRank.toDbEntity()
    )