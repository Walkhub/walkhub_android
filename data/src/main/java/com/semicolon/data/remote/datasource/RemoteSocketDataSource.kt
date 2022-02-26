package com.semicolon.data.remote.datasource

interface RemoteSocketDataSource {

    fun sendCheering(userId: Int)

    fun receiveCheering(): String

    fun disConnected()

    fun connectSocket()
}