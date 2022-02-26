package com.semicolon.domain.repository

import kotlinx.coroutines.flow.Flow

interface SocketRepository {

    fun cheering(userId : Int): Flow<String>

    fun connectedSocket()

    fun disConnectedSocket()
}