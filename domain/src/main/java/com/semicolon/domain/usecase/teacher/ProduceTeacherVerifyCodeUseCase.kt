package com.semicolon.domain.usecase.teacher

import com.semicolon.domain.repository.TeacherRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProduceTeacherVerifyCodeUseCase @Inject constructor(
    private val teacherRepository: TeacherRepository
): UseCase<Unit,Flow<String>>() {
    override suspend fun execute(data: Unit): Flow<String> =
        teacherRepository.produceTeacherVerifyCode()
}