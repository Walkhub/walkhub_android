package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.FindUserAccountEntity
import com.semicolon.domain.entity.users.UserOwnBadgeEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindUserAccountUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<String, Flow<FindUserAccountEntity>>() {

    override suspend fun execute(phoneNumber: String): Flow<FindUserAccountEntity> =
        userRepository.findUserAccount(phoneNumber)

}