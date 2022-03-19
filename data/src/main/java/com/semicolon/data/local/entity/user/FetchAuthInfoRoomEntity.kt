package com.semicolon.data.local.entity.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.users.FetchAuthInfoEntity

@Entity(tableName = "authInfo")
data class FetchAuthInfoRoomEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val accountId: String,
    val phoneNumber: String
)

fun FetchAuthInfoRoomEntity.toEntity() =
    FetchAuthInfoEntity(
        accountId = accountId,
        phoneNumber = phoneNumber
    )

fun FetchAuthInfoEntity.toDbEntity() =
    FetchAuthInfoRoomEntity(
        accountId = accountId,
        phoneNumber = phoneNumber
    )