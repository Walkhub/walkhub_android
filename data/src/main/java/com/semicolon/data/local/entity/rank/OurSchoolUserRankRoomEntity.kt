package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity

@Entity(tableName = "ourschoolUserRank")
data class OurSchoolUserRankRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val isJoinedClass: Boolean,
    @Embedded val myRanking: Ranking?,
    val rankingList: List<Ranking>
) {
    data class Ranking(
        val name: String,
        val profileImageUrl: String,
        val ranking: Int,
        val userId: Int,
        val walkCount: Int
    )

    fun Ranking.toEntity() =
        OurSchoolUserRankEntity.Ranking(
            name = name,
            profileImageUrl = profileImageUrl,
            ranking = ranking,
            userId = userId,
            walkCount = walkCount
        )
}

fun OurSchoolUserRankRoomEntity.toEntity() =
    OurSchoolUserRankEntity(
        isJoinedClass = isJoinedClass,
        myRanking = myRanking?.toEntity(),
        rankingList = rankingList.map { it.toEntity() }
    )

fun OurSchoolUserRankEntity.Ranking.toDbEntity() =
    profileImageUrl?.let {
        OurSchoolUserRankRoomEntity.Ranking(
            name = name,
            profileImageUrl = it,
            ranking = ranking,
            userId = userId,
            walkCount = walkCount
        )
    }

fun OurSchoolUserRankEntity.toDbEntity() =
    rankingList.map { it.toDbEntity() }?.let {
        OurSchoolUserRankRoomEntity(
            isJoinedClass = isJoinedClass,
            myRanking = myRanking?.toDbEntity(),
            rankingList = it as List<OurSchoolUserRankRoomEntity.Ranking>
        )
    }

