package org.llschall.dmixtrip.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import java.util.Date

class ConnectionModel : ViewModel() {

    val logs = createLogs().toMutableStateList()

    fun onStart() {
        log("started")
    }

    fun log(msg: String) {
        val now = Date().time
        logs.add("$now >  $msg")
        logs.removeFirst()
    }

    private fun createLogs() = List(7) { "*" }

}