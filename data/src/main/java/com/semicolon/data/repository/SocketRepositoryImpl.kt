package com.semicolon.data.repository

import com.semicolon.data.remote.datasource.RemoteSocketDataSource
import com.semicolon.domain.repository.SocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor(
    private val remoteSocketDataSource: RemoteSocketDataSource
): SocketRepository {

    override fun cheering(userId: Int): Flow<String> {
        remoteSocketDataSource.sendCheering(userId)
        return flow { emit(remoteSocketDataSource.receiveCheering()) }
    }

    override fun connectedSocket() =
        remoteSocketDataSource.connectSocket()


    override fun disConnectedSocket() =
        remoteSocketDataSource.disConnected()

}