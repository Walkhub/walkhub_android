package com.semicolon.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface SocketRepository {

    fun cheering(userId: Int)

    fun receiveCheering(): SharedFlow<String>

    fun receiveError(): Flow<List<String>>

    fun connectSocket()

    fun disConnectSocket()
}