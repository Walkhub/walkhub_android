package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.level.LevelEntity

interface LocalLevelDataSource {

    suspend fun fetchLevelList(): List<LevelEntity>

    suspend fun saveLevelList(remoteLevelList: List<LevelEntity>)
}