package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.SchoolRankEntity

@Entity(tableName = "schoolRank")
data class SchoolRankRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val mySchoolRank: MySchoolRank
) {

    data class MySchoolRank(
        val schoolId: Int,
        val name: String,
        val logoImageUrl: String,
        val grade: Int,
        val classNum: Int
    )

    fun MySchoolRank.toEntity() =
        SchoolRankEntity.MySchoolRank(
            schoolId = schoolId,
            name = name,
            logoImageUrl = logoImageUrl,
            grade = grade,
            classNum = classNum
        )
}

fun SchoolRankRoomEntity.toEntity() =
    SchoolRankEntity(
        mySchoolRank = mySchoolRank.toEntity()
    )

fun SchoolRankEntity.MySchoolRank.toDbEntity() =
    SchoolRankRoomEntity.MySchoolRank(
        schoolId = schoolId,
        name = name,
        logoImageUrl = logoImageUrl,
        grade = grade,
        classNum = classNum
    )

fun SchoolRankEntity.toDbEntity() =
    SchoolRankRoomEntity(
        mySchoolRank = mySchoolRank.toDbEntity()
    )
