package com.semicolon.data.repository

import com.semicolon.data.remote.datasource.RemoteSocketDataSource
import com.semicolon.domain.repository.SocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor(
    private val remoteSocketDataSource: RemoteSocketDataSource
) : SocketRepository {

    override fun cheering(userId: Int) =
        remoteSocketDataSource.sendCheering(userId)

    override fun receiveCheering(): Flow<String> {
        return flow { emit(remoteSocketDataSource.receiveCheering()) }
    }

    override fun receiveError(): Flow<List<String>> {
        return flow { emit(remoteSocketDataSource.receiveError()) }
    }

    override fun connectedSocket() =
        remoteSocketDataSource.connectSocket()


    override fun disConnectedSocket() =
        remoteSocketDataSource.disconnected()

}