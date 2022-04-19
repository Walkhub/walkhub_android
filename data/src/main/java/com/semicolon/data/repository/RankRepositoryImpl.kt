package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalRankDataSource
import com.semicolon.data.remote.datasource.RemoteRankDataSource
import com.semicolon.data.remote.response.ranks.inquiryRank.school.toEntity
import com.semicolon.data.remote.response.ranks.inquiryRank.user.toEntity
import com.semicolon.data.remote.response.ranks.search.user.toEntity
import com.semicolon.data.remote.response.ranks.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.rank.*
import com.semicolon.domain.param.rank.FetchOurSchoolUserRankParam
import com.semicolon.domain.param.rank.FetchUserRankParam
import com.semicolon.domain.param.rank.SearchUserParam
import com.semicolon.domain.param.user.FetchSchoolRankAndSearchParam
import com.semicolon.domain.repository.RankRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RankRepositoryImpl @Inject constructor(
    private val localRankDataSource: LocalRankDataSource,
    private val remoteRankDataSource: RemoteRankDataSource
) : RankRepository {
    override suspend fun fetchMySchoolRank(schoolDateType: String): Flow<FetchMySchoolRankEntity> =
        OfflineCacheUtil<FetchMySchoolRankEntity>()
            .remoteData { remoteRankDataSource.fetchMySchoolRank(schoolDateType).toEntity() }
            .localData { localRankDataSource.fetchMySchoolRank() }
            .doOnNeedRefresh { localRankDataSource.insertFetchMySchoolRank(it) }
            .createFlow()

    override suspend fun fetchSchoolRankAndSearch(fetchSchoolRankAndSearchParam: FetchSchoolRankAndSearchParam): Flow<SchoolRankAndSearchEntity> =
        OfflineCacheUtil<SchoolRankAndSearchEntity>()
            .remoteData {
                remoteRankDataSource.fetchSchoolRankAndSearch(
                    fetchSchoolRankAndSearchParam.name!!,
                    fetchSchoolRankAndSearchParam.schoolDateType
                ).toEntity()
            }
            .localData { localRankDataSource.fetchSchoolAndSearchRank() }
            .doOnNeedRefresh { localRankDataSource.insertSchoolAndSearchRank(it) }
            .createFlow()

    override suspend fun fetchUserRank(fetchUserRankParam: FetchUserRankParam): Flow<UserRankEntity> =
        OfflineCacheUtil<UserRankEntity>()
            .remoteData {
                remoteRankDataSource.fetchUserRank(
                    fetchUserRankParam.schoolId,
                    fetchUserRankParam.dateType.toString()
                ).toEntity()
            }
            .localData { localRankDataSource.fetchUserRank() }
            .doOnNeedRefresh { localRankDataSource.insertUserRank(it) }
            .createFlow()

    override suspend fun fetchOurSchoolUserRank(fetchOurSchoolUserRankParam: FetchOurSchoolUserRankParam): Flow<OurSchoolUserRankEntity> =
        OfflineCacheUtil<OurSchoolUserRankEntity>()
            .remoteData {
                remoteRankDataSource.fetchOurSchoolUserRank(
                    fetchOurSchoolUserRankParam.scope.toString(),
                    fetchOurSchoolUserRankParam.dateType.toString()
                ).toEntity()
            }
            .localData { localRankDataSource.fetchOurSchoolUserRank() }
            .doOnNeedRefresh { localRankDataSource.insertOurSchoolUserRank(it) }
            .createFlow()

    override suspend fun searchUser(searchUserParam: SearchUserParam): Flow<SearchUserEntity> =
        OfflineCacheUtil<SearchUserEntity>()
            .remoteData {
                remoteRankDataSource.searchUser(
                    searchUserParam.schoolId,
                    searchUserParam.name,
                    searchUserParam.moreDateType.toString()
                ).toEntity()
            }
            .localData { localRankDataSource.searchUser() }
            .doOnNeedRefresh { localRankDataSource.insertSearchUser(it) }
            .createFlow()

}
