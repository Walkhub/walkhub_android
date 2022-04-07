package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.SearchSchoolEntity

@Entity(tableName = "searchSchool")
data class SearchSchoolRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val schoolList: List<SchoolInfo>
) {
    data class SchoolInfo(
        val schoolId: Int,
        val schoolName: String,
        val ranking: Int,
        val logoImageUrl: String,
        val walkCount: Int,
        val userCount: Int
    )

    fun SchoolInfo.toEntity() =
        SearchSchoolEntity.SchoolInfo(
            schoolId = schoolId,
            schoolName = schoolName,
            ranking = ranking,
            logoImageUrl = logoImageUrl,
            walkCount = walkCount,
            userCount = userCount
        )
}

fun SearchSchoolRoomEntity.toEntity() =
    SearchSchoolEntity(
        schoolList = schoolList.map { it.toEntity() }
    )

fun SearchSchoolEntity.SchoolInfo.toDbEntity() =
    SearchSchoolRoomEntity.SchoolInfo(
        schoolId = schoolId,
        schoolName = schoolName,
        ranking = ranking,
        logoImageUrl = logoImageUrl,
        walkCount = walkCount,
        userCount = userCount
    )

fun SearchSchoolEntity.toDbEntity() =
    SearchSchoolRoomEntity(
        schoolList = schoolList.map { it.toDbEntity() }
    )
