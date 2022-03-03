package com.semicolon.domain.usecase.socket

import com.semicolon.domain.repository.SocketRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReceiveCheeringUseCase @Inject constructor(
    private val socketRepository: SocketRepository
): UseCase<Unit, Flow<String>>() {

    override suspend fun execute(data: Unit): Flow<String> =
        socketRepository.receiveCheering()
}