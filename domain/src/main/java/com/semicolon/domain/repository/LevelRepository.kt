package com.semicolon.domain.repository

import com.semicolon.domain.entity.level.LevelEntity
import kotlinx.coroutines.flow.Flow

interface LevelRepository {
    suspend fun fetchLevelList(): Flow<List<LevelEntity>>
}