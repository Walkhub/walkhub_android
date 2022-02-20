package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.level.LevelEntity
import kotlinx.coroutines.flow.Flow

class LocalLevelDataSourceImpl: LocalLevelDataSource {
    override suspend fun fetchLevelList(): Flow<List<LevelEntity>> {
        TODO("Not yet implemented")
    }
}