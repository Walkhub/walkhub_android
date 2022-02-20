package com.semicolon.data.repository

import com.semicolon.data.remote.datasource.RemoteLevelDataSource
import com.semicolon.domain.entity.level.LevelEntity
import com.semicolon.domain.repository.LevelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LevelRepositoryImpl @Inject constructor(
    private val remoteLevelDataSource: RemoteLevelDataSource
): LevelRepository {

    override suspend fun fetchLevelList(): Flow<List<LevelEntity>> = flow {
        remoteLevelDataSource.fetchLevelList()
    }

    override suspend fun patchMaxLevel(levelId: Int) {
        remoteLevelDataSource.fetchLevelList()
    }
}