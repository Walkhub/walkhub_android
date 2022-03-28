package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.FetchInfoEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Flow<FetchInfoEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchInfoEntity> =
        userRepository.fetchInfo()
}