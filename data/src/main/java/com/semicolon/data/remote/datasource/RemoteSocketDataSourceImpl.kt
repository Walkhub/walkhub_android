package com.semicolon.data.remote.datasource

import android.util.Log
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import javax.inject.Inject

class RemoteSocketDataSourceImpl @Inject constructor(
    private val socket: Socket
) : RemoteSocketDataSource {

    var receiveMessage: String = ""

    var errorMessage: String = ""
    var code: String = ""
    var status: Int = 0

    private val onReceiveMessage: Emitter.Listener = Emitter.Listener {
        val json = JSONObject(it[0].toString())
        receiveMessage = json.getString("message")

    }

    private val onErrorMessage: Emitter.Listener = Emitter.Listener {
        val json = JSONObject(it[0].toString())
        status = json.getInt("status")
        code = json.getString("code")
        errorMessage = json.getString("message")
    }

    override fun sendCheering(userId: Int) {
        val data = JSONObject()
        socket.connect()
        data.put("user_id", 10)
        socket.emit("cheering", data)
    }

    override fun receiveCheering(): String {
        socket.on("new_cheering", onReceiveMessage)

        return receiveMessage
    }

    override fun receiveError(): List<String> {
        socket.on("error", onErrorMessage)

        return listOf(errorMessage,code,status.toString())
    }

    override fun disconnected() {
        socket.disconnect()
    }

    override fun connectSocket() {
        socket.connect()
        socket.on(Socket.EVENT_CONNECT) {
            Log.d("success", "success")
        }.on(Socket.EVENT_CONNECT_ERROR) {
            Log.d("fail", it.contentToString())
            println(it.contentToString())
        }
    }

}