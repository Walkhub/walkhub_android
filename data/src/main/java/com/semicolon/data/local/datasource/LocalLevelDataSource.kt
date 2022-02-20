package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.level.LevelEntity
import kotlinx.coroutines.flow.Flow

interface LocalLevelDataSource {

    suspend fun fetchLevelList(): Flow<List<LevelEntity>>
}