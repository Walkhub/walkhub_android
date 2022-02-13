package com.semicolon.domain.usecase.socket

import com.semicolon.domain.repository.SocketRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class ConnectionSocketUseCase @Inject constructor(
    private val socketRepository: SocketRepository
) : UseCase<String, Unit>() {
    override suspend fun execute(data: String) =
        socketRepository.connectionSocket(data)
}
