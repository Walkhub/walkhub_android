package com.semicolon.data.remote.datasource

import kotlinx.coroutines.flow.SharedFlow

interface RemoteSocketDataSource {

    fun sendCheering(userId: Int)

    fun receiveCheering(): SharedFlow<String>

    fun receiveError(): List<String>

    fun disconnect()

    fun connectSocket()
}