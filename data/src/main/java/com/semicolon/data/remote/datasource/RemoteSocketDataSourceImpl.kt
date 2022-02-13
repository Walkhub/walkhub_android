package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.SocketApi
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteSocketDataSourceImpl @Inject constructor(
    private val socketApi: SocketApi
) : RemoteSocketDataSource {

    override suspend fun connectionSocket(transport: String) =
        HttpHandler<Unit>()
            .httpRequest { socketApi.connectionSocket(transport) }
            .sendRequest()
}
