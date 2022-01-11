package com.semicolon.domain.usecase.teacher

import com.semicolon.domain.param.teacher.CreateClassParam
import com.semicolon.domain.repository.TeacherRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateClassUseCase @Inject constructor(
    private val teacherRepository: TeacherRepository
): UseCase<CreateClassParam,Flow<String>>() {
    override suspend fun execute(data: CreateClassParam): Flow<String> =
        teacherRepository.createClass(data)
}