package com.semicolon.domain.usecase.teacher

import com.semicolon.domain.param.teacher.DeleteClassParam
import com.semicolon.domain.repository.TeacherRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class DeleteClassUseCase @Inject constructor(
    private val teacherRepository: TeacherRepository
): UseCase<DeleteClassParam,Unit>() {
    override suspend fun execute(data: DeleteClassParam) =
        teacherRepository.deleteClass(data)
}