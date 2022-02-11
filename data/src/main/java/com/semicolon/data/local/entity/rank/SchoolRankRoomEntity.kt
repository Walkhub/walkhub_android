package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.SchoolRankEntity

@Entity(tableName = "schoolRank")
data class SchoolRankRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val mySchoolRank: MySchoolRank,
    @Embedded val schoolRankList: List<SchoolRank>
) {
    data class SchoolRank(
        val agencyCode: String,
        val logoImageUrl: String,
        val name: String,
        val walkCount: Int
    )

    data class MySchoolRank(
        val agencyCode: String,
        val logoImageUrl: String,
        val name: String,
        val rank: Int,
        val walkCount: Int
    )

    fun SchoolRank.toEntity() =
        SchoolRankEntity.SchoolRank(
            agencyCode = agencyCode,
            logoImageUrl = logoImageUrl,
            name = name,
            walkCount = walkCount
        )

    fun MySchoolRank.toEntity() =
        SchoolRankEntity.MySchoolRank(
            agencyCode = agencyCode,
            logoImageUrl = logoImageUrl,
            name = name,
            rank = rank,
            walkCount = walkCount
        )
}

fun SchoolRankRoomEntity.toEntity() =
    SchoolRankEntity(
        mySchoolRank = mySchoolRank.toEntity(),
        schoolRankList = schoolRankList.map { it.toEntity() }
    )

fun SchoolRankEntity.MySchoolRank.toDbEntity() =
    SchoolRankRoomEntity.MySchoolRank(
        agencyCode = agencyCode,
        logoImageUrl = logoImageUrl,
        name = name,
        rank = rank,
        walkCount = walkCount
    )

fun SchoolRankEntity.SchoolRank.toDbEntity() =
    SchoolRankRoomEntity.SchoolRank(
        agencyCode = agencyCode,
        logoImageUrl = logoImageUrl,
        name = name,
        walkCount = walkCount
    )

fun SchoolRankEntity.toDbEntity() =
    SchoolRankRoomEntity(
        mySchoolRank = mySchoolRank.toDbEntity(),
        schoolRankList = schoolRankList.map { it.toDbEntity() }
    )
