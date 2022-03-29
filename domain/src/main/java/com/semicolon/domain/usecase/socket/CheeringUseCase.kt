package com.semicolon.domain.usecase.socket

import com.semicolon.domain.repository.SocketRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class CheeringUseCase @Inject constructor(
    private val socketRepository: SocketRepository
) : UseCase<Int, Unit>() {

    override suspend fun execute(data: Int) =
        socketRepository.cheering(data)
}