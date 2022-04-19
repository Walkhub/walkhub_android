package com.semicolon.data.local.entity.rank

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.UserRankEntity

@Entity(tableName = "userRank")
data class UserRankRoomEntity(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    val rankList: List<UserRank>
) {
    data class UserRank(
        val userId: Int,
        val name: String,
        val ranking: Int,
        val profileImageUrl: String,
        val walkCount: Int
    )

    fun UserRank.toEntity() =
        UserRankEntity.UserRank(
            userId = userId,
            name = name,
            ranking = ranking,
            profileImageUrl = profileImageUrl,
            walkCount = walkCount
        )
}

fun UserRankRoomEntity.toEntity() =
    UserRankEntity(
        rankList = rankList.map { it.toEntity() }
    )

fun UserRankEntity.UserRank.toDbEntity() =
    UserRankRoomEntity.UserRank(
        userId = userId,
        name = name,
        ranking = ranking,
        profileImageUrl = profileImageUrl,
        walkCount = walkCount
    )

fun UserRankEntity.toDbEntity() =
    UserRankRoomEntity(
        rankList = rankList.map { it.toDbEntity() }
    )