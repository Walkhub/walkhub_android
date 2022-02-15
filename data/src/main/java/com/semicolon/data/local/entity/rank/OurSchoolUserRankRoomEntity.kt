package com.semicolon.data.local.entity.rank

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity

@Entity(tableName = "ourschoolUserRank")
data class OurSchoolUserRankRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val myRanking: Ranking,
    @Embedded val rankingList: List<Ranking>
) {
    data class Ranking(
        val classNum: Int,
        val grade: Int,
        val name: String,
        val profileImageUrl: String,
        val ranking: Int,
        val userId: Int,
        val walkCount: Int
    )

    fun Ranking.toEntity() =
        OurSchoolUserRankEntity.Ranking(
            classNum = classNum,
            grade = grade,
            name = name,
            profileImageUrl = profileImageUrl,
            ranking = ranking,
            userId = userId,
            walkCount = walkCount
        )
}

fun OurSchoolUserRankRoomEntity.toEntity() =
    OurSchoolUserRankEntity(
        myRanking = myRanking.toEntity(),
        rankingList = rankingList.map { it.toEntity() }
    )

fun OurSchoolUserRankEntity.Ranking.toDbEntity() =
    OurSchoolUserRankRoomEntity.Ranking(
        classNum = classNum,
        grade = grade,
        name = name,
        profileImageUrl = profileImageUrl,
        ranking = ranking,
        userId = userId,
        walkCount = walkCount
    )

fun OurSchoolUserRankEntity.toDbEntity() =
    OurSchoolUserRankRoomEntity(
        myRanking = myRanking.toDbEntity(),
        rankingList = rankingList.map { it.toDbEntity() }
    )
