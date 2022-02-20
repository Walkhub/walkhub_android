package com.semicolon.data.repository

import com.semicolon.domain.entity.level.LevelEntity
import com.semicolon.domain.repository.LevelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LevelRepositoryImpl @Inject constructor(
    
): LevelRepository {
    override suspend fun fetchLevelList(): Flow<List<LevelEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun patchMaxLevel(levelId: Int) {
        TODO("Not yet implemented")
    }
}