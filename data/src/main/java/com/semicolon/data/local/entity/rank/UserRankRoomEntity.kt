package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.UserRankEntity

@Entity(tableName = "userRank")
data class UserRankRoomEntity(
    @PrimaryKey (autoGenerate = true) var id: Int = 0,
    @Embedded val rankList: List<UserRank>
) {
    data class UserRank(
        val classNum: Int,
        val grade: Int,
        val name: String,
        val profileImageUrl: String,
        val rank: Int,
        val userId: Int,
        val walkCount: Int
    )

    fun UserRank.toEntity() =
        UserRankEntity.UserRank(
            classNum = classNum,
            grade = grade,
            name = name,
            profileImageUrl = profileImageUrl,
            rank = rank,
            userId = userId,
            walkCount = walkCount
        )
}

fun UserRankRoomEntity.toEntity() =
    UserRankEntity(
        rankList = rankList.map { it.toEntity() }
    )

fun UserRankEntity.UserRank.toDbEntity() =
    UserRankRoomEntity.UserRank(
        classNum = classNum,
        grade = grade,
        name = name,
        profileImageUrl = profileImageUrl,
        rank = rank,
        userId = userId,
        walkCount = walkCount
    )

fun UserRankEntity.toDbEntity() =
    UserRankRoomEntity(
        rankList = rankList.map { it.toDbEntity() }
    )