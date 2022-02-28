package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalRankDataSource
import com.semicolon.data.remote.datasource.RemoteRankDataSource
import com.semicolon.data.remote.response.ranks.inquiryRank.school.toEntity
import com.semicolon.data.remote.response.ranks.inquiryRank.user.toEntity
import com.semicolon.data.remote.response.ranks.search.school.toEntity
import com.semicolon.data.remote.response.ranks.search.user.toEntity
import com.semicolon.data.remote.response.ranks.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.rank.*
import com.semicolon.domain.enum.DateType
import com.semicolon.domain.param.rank.FetchOurSchoolUserRankParam
import com.semicolon.domain.param.rank.FetchUserRankParam
import com.semicolon.domain.param.rank.SearchSchoolParam
import com.semicolon.domain.param.rank.SearchUserParam
import com.semicolon.domain.repository.RankRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RankRepositoryImpl @Inject constructor(
    private val localRankDataSource: LocalRankDataSource,
    private val remoteRankDataSource: RemoteRankDataSource
) : RankRepository {
    override suspend fun fetchSchoolRank(dateType: DateType): Flow<SchoolRankEntity> =
        OfflineCacheUtil<SchoolRankEntity>()
            .remoteData { remoteRankDataSource.fetchSchoolRank(dateType.toString()).toEntity() }
            .localData { localRankDataSource.fetchSchoolRank() }
            .doOnNeedRefresh { localRankDataSource.insertSchoolRank(it) }
            .createFlow()

    override suspend fun searchSchool(searchSchoolParam: SearchSchoolParam): Flow<SearchSchoolEntity> =
        OfflineCacheUtil<SearchSchoolEntity>()
            .remoteData {
                remoteRankDataSource.searchSchool(
                    searchSchoolParam.name,
                    searchSchoolParam.moreDateType.toString()
                ).toEntity()
            }
            .localData { localRankDataSource.searchSchool() }
            .doOnNeedRefresh { localRankDataSource.insertSearchSchool(it) }
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
                    searchUserParam.dateType.toString()
                ).toEntity()
            }
            .localData { localRankDataSource.searchUser() }
            .doOnNeedRefresh { localRankDataSource.insertSearchUser(it) }
            .createFlow()

}
