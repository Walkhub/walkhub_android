package com.semicolon.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

interface SocketApi {
    @GET("/socket.io")
    suspend fun connectionSocket(
        @Query("transport") transport: String
    )
}