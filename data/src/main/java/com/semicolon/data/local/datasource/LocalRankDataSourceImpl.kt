package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.RankDao
import com.semicolon.data.local.entity.rank.*
import com.semicolon.domain.entity.rank.*
import javax.inject.Inject

class LocalRankDataSourceImpl @Inject constructor(
    private val rankDao: RankDao
) : LocalRankDataSource {
    override suspend fun fetchMySchoolRank(): FetchMySchoolRankEntity =
        rankDao.fetchMySchoolRank().toEntity()

    override suspend fun insertFetchMySchoolRank(fetchMySchoolRankEntity: FetchMySchoolRankEntity) {
        rankDao.insertFetchMySchoolRank(fetchMySchoolRankEntity.toDbEntity())
    }

    override suspend fun fetchSchoolAndSearchRank(): SchoolRankAndSearchEntity =
        rankDao.fetchSchoolRankAndSearch().toEntity()

    override suspend fun insertSchoolAndSearchRank(schoolRankAndSearch: SchoolRankAndSearchEntity) {
        rankDao.insertSchoolAndSearch(schoolRankAndSearch.toDbEntity())
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