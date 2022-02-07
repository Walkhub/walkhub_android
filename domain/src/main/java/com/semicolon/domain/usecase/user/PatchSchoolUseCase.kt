package com.semicolon.domain.usecase.user

import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PatchSchoolUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<String, Unit>() {

    override suspend fun execute(agencyCode: String) =
        userRepository.patchSchool(agencyCode)
}