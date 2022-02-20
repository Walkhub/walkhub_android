package com.semicolon.data.remote.datasource

import com.semicolon.domain.entity.level.LevelEntity

interface RemoteLevelDataSource {

    suspend fun fetchLevelList(): List<LevelEntity>

    suspend fun saveLevelList(levelList: List<LevelEntity>)

    suspend fun patchMaxLevel(levelId: Int)
}