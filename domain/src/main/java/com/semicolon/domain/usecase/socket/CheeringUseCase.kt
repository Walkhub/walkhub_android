package com.semicolon.domain.usecase.socket

import com.semicolon.domain.repository.SocketRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheeringUseCase @Inject constructor(
    private val socketRepository: SocketRepository
) : UseCase<Int, Flow<String>>() {

    override suspend fun execute(data: Int): Flow<String> =
        socketRepository.cheering(data)
}