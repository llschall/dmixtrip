package org.llschall.dmixtrip.model

import android.content.Context
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import org.llschall.dmixtrip.model.ConnectionHandler.Companion.handler
import java.util.Date

class ConnectionModel() : ViewModel() {

    val logs = createLogs().toMutableStateList()

    var status = createStatus().toMutableStateList()

    fun onStart(context: Context) {
        log("started")
        log(handler.setup(context))
    }

    fun onStatus() {
        status[0] = handler.status()
    }

    fun onConnect() {
        status[0] = handler.connect()
    }

    fun onSend() {
        handler.send()
    }

    fun log(msg: String) {
        val now = Date().time
        logs.add("$now >  $msg")
        logs.removeFirst()
    }

    private fun createLogs() = List(12) { "*" }
    private fun createStatus() = List(1) { "*" }

}