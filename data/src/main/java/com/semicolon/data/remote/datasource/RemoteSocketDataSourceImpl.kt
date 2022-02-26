package com.semicolon.data.remote.datasource

import android.util.Log
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import javax.inject.Inject

class RemoteSocketDataSourceImpl @Inject constructor(
    private val socket: Socket
): RemoteSocketDataSource {

    var message: String= ""

    private val onReceiveMessage: Emitter.Listener = Emitter.Listener {
        message = it[0] as String
    }

    override fun sendCheering(userId: Int) {
        val data = JSONObject()
        data.put("user_id",1)
        socket.emit("cheering",data)
    }

    override fun receiveCheering(): String {
        socket.on("new_cheering",onReceiveMessage)
        socket.on("error",onReceiveMessage)

        return message
    }

    override fun disConnected() {
        socket.disconnect()
        socket.off()
    }

    override fun connectSocket(){
        socket.connect()
        socket.on(Socket.EVENT_CONNECT) {
            Log.d("success","success")
        }.on(Socket.EVENT_CONNECT_ERROR){
            Log.d("fail",it.contentToString())
            println(it.contentToString())
        }
    }

}