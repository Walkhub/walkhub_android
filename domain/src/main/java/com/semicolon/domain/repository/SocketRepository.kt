package com.semicolon.domain.repository

interface SocketRepository {
    suspend fun connectionSocket(transport: String)
}