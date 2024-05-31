package org.llschall.dmixtrip.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConnectionModel : ViewModel() {

    val logs = createLogs().toMutableStateList()

    fun onStart() {
        log("started")
    }

    fun log(msg: String) {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
        val date = now.format(formatter)

        logs.add("$date >  $msg")
        logs.removeFirst()
    }

    private fun createLogs() = List(7) { "*" }

}