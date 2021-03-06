package com.semicolon.data.repository

import com.semicolon.data.remote.datasource.RemoteSocketDataSource
import com.semicolon.domain.repository.SocketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor(
    private val remoteSocketDataSource: RemoteSocketDataSource
) : SocketRepository {

    override fun cheering(userId: Int) =
        remoteSocketDataSource.sendCheering(userId)

    override fun receiveCheering(): SharedFlow<String> {
        return remoteSocketDataSource.receiveCheering()
    }

    override fun receiveError(): Flow<List<String>> {
        return flow { emit(remoteSocketDataSource.receiveError()) }
    }

    override fun connectSocket() =
        remoteSocketDataSource.connectSocket()


    override fun disConnectSocket() =
        remoteSocketDataSource.disconnect()

}