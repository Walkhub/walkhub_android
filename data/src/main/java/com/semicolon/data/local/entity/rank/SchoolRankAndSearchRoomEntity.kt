package com.semicolon.data.local.entity.rank

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.SchoolRankAndSearchEntity

@Entity(tableName = "schoolRank")
data class SchoolRankAndSearchRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val schoolRankList: List<SchoolRank>
) {
    data class SchoolRank(
        val schoolId: Int,
        val schoolName: String,
        val ranking: Int,
        val logoImageUrl: String?,
        val walkCount: Int,
        val userCount: Int
    )

    fun SchoolRank.toEntity() =
        SchoolRankAndSearchEntity.SchoolRank(
            schoolId = schoolId,
            schoolName = schoolName,
            ranking = ranking,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount,
            userCount = userCount
        )
}

fun SchoolRankAndSearchRoomEntity.toEntity() =
    SchoolRankAndSearchEntity(
        schoolRankList = schoolRankList.map { it.toEntity() }
    )

fun SchoolRankAndSearchEntity.SchoolRank.toDbEntity() =
    SchoolRankAndSearchRoomEntity.SchoolRank(
        schoolId = schoolId,
        schoolName = schoolName,
        ranking = ranking,
        logoImageUrl = logoImageUrl,
        walkCount = walkCount,
        userCount = userCount
    )

fun SchoolRankAndSearchEntity.toDbEntity() =
    SchoolRankAndSearchRoomEntity(
        schoolRankList = schoolRankList.map { it.toDbEntity() }
    )
