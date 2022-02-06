package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMypageUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Flow<UserMyPageEntity>>() {

    override suspend fun execute(data: Unit): Flow<UserMyPageEntity> =
        userRepository.fetchMyPage()
}