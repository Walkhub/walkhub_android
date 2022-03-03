package com.semicolon.domain.repository

import kotlinx.coroutines.flow.Flow

interface SocketRepository {

    fun cheering(userId: Int)

    fun receiveCheering(): Flow<String>

    fun receiveError(): Flow<List<String>>

    fun connectedSocket()

    fun disConnectedSocket()
}