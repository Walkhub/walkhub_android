package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.LevelApi
import com.semicolon.data.remote.response.level.toEntity
import com.semicolon.domain.entity.level.LevelEntity
import javax.inject.Inject

class RemoteLevelDataSourceImpl @Inject constructor(
    private val levelApi: LevelApi
) : RemoteLevelDataSource {

    override suspend fun fetchLevelList(): List<LevelEntity> =
        levelApi.fetchLevelList().toEntity()

    override suspend fun patchMaxLevel(levelId: Int) {
        levelApi.patchMaxLevel(levelId)
    }
}