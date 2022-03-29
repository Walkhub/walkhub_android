package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.FetchUserHealthEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUserHealthUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Flow<FetchUserHealthEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchUserHealthEntity> =
        userRepository.fetchUserHealth()
}