package com.semicolon.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.users.FetchUserHealthEntity

@Entity(tableName = "userHealth")
data class FetchUserHealthRoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val height: Double,
    val weight: Double,
    val sex: String
)

fun FetchUserHealthRoomEntity.toEntity() =
    FetchUserHealthEntity(
        height = height,
        weight = weight,
        sex = sex
    )

fun FetchUserHealthEntity.toDbEntity() =
    FetchUserHealthRoomEntity(
        height = height,
        weight = weight,
        sex = sex
    )
