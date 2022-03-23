package com.semicolon.domain.usecase.level

import com.semicolon.domain.entity.level.LevelEntity
import com.semicolon.domain.repository.LevelRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchLevelListUseCase @Inject constructor(
    private val levelRepository: LevelRepository
) : UseCase<Unit, Flow<List<LevelEntity>>>() {

    override suspend fun execute(data: Unit): Flow<List<LevelEntity>> =
        levelRepository.fetchLevelList()
}