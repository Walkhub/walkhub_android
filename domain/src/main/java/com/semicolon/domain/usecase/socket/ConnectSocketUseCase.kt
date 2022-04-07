package com.semicolon.domain.usecase.socket

import com.semicolon.domain.repository.SocketRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class ConnectSocketUseCase @Inject constructor(
    private val socketRepository: SocketRepository
) : UseCase<Unit, Unit>() {

    override suspend fun execute(data: Unit) =
        socketRepository.connectSocket()
}