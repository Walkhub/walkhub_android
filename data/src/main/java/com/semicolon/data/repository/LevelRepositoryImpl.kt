package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalLevelDataSource
import com.semicolon.data.remote.datasource.RemoteLevelDataSource
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.level.LevelEntity
import com.semicolon.domain.repository.LevelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LevelRepositoryImpl @Inject constructor(
    private val remoteLevelDataSource: RemoteLevelDataSource,
    private val localLevelDataSource: LocalLevelDataSource
) : LevelRepository {

    override suspend fun fetchLevelList(): Flow<List<LevelEntity>> =
        OfflineCacheUtil<List<LevelEntity>>()
            .remoteData { remoteLevelDataSource.fetchLevelList() }
            .localData { localLevelDataSource.fetchLevelList() }
            .doOnNeedRefresh { localLevelDataSource.saveLevelList(it) }
            .createFlow()

    override suspend fun patchMaxLevel(levelId: Int) {
        remoteLevelDataSource.patchMaxLevel(levelId)
    }
}