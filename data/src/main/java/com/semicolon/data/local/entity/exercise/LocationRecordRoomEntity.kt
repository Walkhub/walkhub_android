package com.semicolon.data.local.entity.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationRecordRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val latitude: Double,
    val longitude: Double
)

fun LocationRecordRoomEntity.toEntity() =
    LocationRecordEntity(
        latitude = latitude,
        longitude = longitude
    )

fun LocationRecordEntity.toRoomEntity() =
    LocationRecordRoomEntity(
        latitude = latitude,
        longitude = longitude
    )