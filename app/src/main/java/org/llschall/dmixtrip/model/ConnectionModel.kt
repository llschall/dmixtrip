package org.llschall.dmixtrip.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ConnectionModel : ViewModel() {

    val logs = createLogs().toMutableStateList()

    fun onStart() {
        logs.add("-")
        logs.removeFirst()
    }

    private fun createLogs() = List(5) { "*" }

}