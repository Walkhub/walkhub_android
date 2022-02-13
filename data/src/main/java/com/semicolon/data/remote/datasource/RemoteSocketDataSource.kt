package com.semicolon.data.remote.datasource

interface RemoteSocketDataSource {
    suspend fun connectionSocket(transport: String)
}
