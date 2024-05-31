package org.llschall.dmixtrip.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import org.llschall.dmixtrip.model.ConnectionHandler.Companion.handler
import java.util.Date

class ConnectionModel : ViewModel() {

    val logs = createLogs().toMutableStateList()

    fun onStart() {
        log("started")
    }

    fun onConnect() {
        log("connect")
        log(handler.setup())
    }


    fun log(msg: String) {
        val now = Date().time
        logs.add("$now >  $msg")
        logs.removeFirst()
    }

    private fun createLogs() = List(7) { "*" }

}