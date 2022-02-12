package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.RankDao
import com.semicolon.data.local.entity.rank.*
import com.semicolon.domain.entity.rank.*
import javax.inject.Inject

class LocalRankDataSourceImpl @Inject constructor(
    private val rankDao: RankDao
) : LocalRankDataSource {
    override suspend fun fetchSchoolRank(): SchoolRankEntity =
        rankDao.fetchSchoolRank().toEntity()

    override suspend fun insertSchoolRank(schoolRank: SchoolRankEntity) {
        rankDao.insertSchoolRank(schoolRank.toDbEntity())
    }

    override suspend fun searchSchool(): SearchSchoolEntity =
        rankDao.searchSchool().toEntity()

    override suspend fun insertSearchSchool(searchSchool: SearchSchoolEntity) {
        rankDao.insertSearchSchool(searchSchool.toDbEntity())
    }

    override suspend fun fetchUserRank(): UserRankEntity =
        rankDao.fetchUserRank().toEntity()

    override suspend fun insertUserRank(userRank: UserRankEntity) {
        rankDao.insertUserRank(userRank.toDbEntity())
    }

    override suspend fun fetchOurSchoolUserRank(): OurSchoolUserRankEntity =
        rankDao.fetchOurSchoolUserRank().toEntity()

    override suspend fun insertOurSchoolUserRank(ourSchoolUserRank: OurSchoolUserRankEntity) {
        rankDao.insertOurSchoolUserRank(ourSchoolUserRank.toDbEntity())
    }

    override suspend fun searchUser(): SearchUserEntity =
        rankDao.searchUser().toEntity()

    override suspend fun insertSearchUser(searchUser: SearchUserEntity) {
        rankDao.insertSearchUser(searchUser.toDbEntity())
    }
}