package com.semicolon.data.remote.datasource

interface RemoteSocketDataSource {

    fun sendCheering(userId: Int)

    fun receiveCheering(): String

    fun disconnected()

    fun connectSocket()
}