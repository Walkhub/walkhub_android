package com.semicolon.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.users.FetchInfoEntity

@Entity(tableName = "userInfo")
data class FetchInfoRoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val name: String,
    val profileImageUrl: String,
    val schoolName: String,
    val grade: Int,
    val classNum: Int
)

fun FetchInfoRoomEntity.toEntity() =
    FetchInfoEntity(
        name = name,
        profileImageUrl = profileImageUrl,
        schoolName = schoolName,
        grade = grade,
        classNum = classNum
    )

fun FetchInfoEntity.toDbEntity() =
    profileImageUrl?.let {
        grade?.let {
            classNum?.let {
                FetchInfoRoomEntity(
                    name = name,
                    profileImageUrl = profileImageUrl!!,
                    schoolName = schoolName,
                    grade = grade!!,
                    classNum = classNum!!
                )
            }
        }
    }