package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.FetchAuthInfoEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAuthInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Flow<FetchAuthInfoEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchAuthInfoEntity> =
        userRepository.fetchAuthInfo()
}