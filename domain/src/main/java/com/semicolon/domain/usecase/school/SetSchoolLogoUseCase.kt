package com.semicolon.domain.usecase.school

import com.semicolon.domain.repository.SchoolRepository
import com.semicolon.domain.usecase.UseCase
import java.io.File
import javax.inject.Inject

class SetSchoolLogoUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
) : UseCase<File, Unit>() {

    override suspend fun execute(data: File) {
        schoolRepository.setSchoolLogo(data)
    }
}