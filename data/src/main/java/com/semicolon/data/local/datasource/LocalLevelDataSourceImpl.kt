package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.LevelDao
import com.semicolon.data.local.entity.level.toEntity
import com.semicolon.data.local.entity.level.toRoomEntity
import com.semicolon.domain.entity.level.LevelEntity
import javax.inject.Inject

class LocalLevelDataSourceImpl @Inject constructor(
    private val levelDao: LevelDao
) : LocalLevelDataSource {

    override suspend fun fetchLevelList(): List<LevelEntity> =
        levelDao.fetchLevelList().toEntity()

    override suspend fun saveLevelList(remoteLevelList: List<LevelEntity>) {
        levelDao.saveLevelList(remoteLevelList.toRoomEntity())
    }
}