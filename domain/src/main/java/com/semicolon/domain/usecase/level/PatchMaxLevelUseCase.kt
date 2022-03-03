package com.semicolon.domain.usecase.level

import com.semicolon.domain.repository.LevelRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PatchMaxLevelUseCase @Inject constructor(
    private val levelRepository: LevelRepository
): UseCase<Int, Unit>() {

    override suspend fun execute(data: Int) {
        levelRepository.patchMaxLevel(data)
    }
}