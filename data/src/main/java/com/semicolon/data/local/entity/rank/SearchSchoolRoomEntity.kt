package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import com.semicolon.domain.entity.rank.SearchSchoolEntity

@Entity(tableName = "searchSchool")
data class SearchSchoolRoomEntity(
    @Embedded val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        val agencyCode: String,
        val logoImageUrl: String,
        val rank: Int,
        val schoolName: String,
        val walkCount: Int
    )

    fun SchoolInfo.toEntity() =
        SearchSchoolEntity.SchoolInfo(
            agencyCode = agencyCode,
            logoImageUrl = logoImageUrl,
            rank = rank,
            schoolName = schoolName,
            walkCount = walkCount
        )
}

fun SearchSchoolRoomEntity.toEntity() =
    SearchSchoolEntity(
        schoolList = schoolList.map { it.toEntity() }
    )

fun SearchSchoolEntity.SchoolInfo.toDbEntity() =
    SearchSchoolRoomEntity.SchoolInfo(
        agencyCode = agencyCode,
        logoImageUrl = logoImageUrl,
        rank = rank,
        schoolName = schoolName,
        walkCount = walkCount
    )

fun SearchSchoolEntity.toDbEntity() =
    SearchSchoolRoomEntity(
        schoolList = schoolList.map { it.toDbEntity() }
    )
