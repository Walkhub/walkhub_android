package com.semicolon.data.local.entity.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationRecordRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val latitude: Double,
    val longitude: Double
)

