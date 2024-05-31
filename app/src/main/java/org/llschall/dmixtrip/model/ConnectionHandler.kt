package org.llschall.dmixtrip.model

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.core.content.ContextCompat

class ConnectionHandler {
    companion object {
        val handler: ConnectionHandler = ConnectionHandler()
    }

    var context: Context? = null
    var service: BluetoothManager? = null
    var adapter: BluetoothAdapter? = null

    fun setup(): String {
        if (context == null) return "context not found"
        try {
            service = ContextCompat.getSystemService(context!!, BluetoothManager::class.java)
            if (service == null) return "service not found"
            adapter = service!!.adapter
            if (adapter == null) return "adapter not found"
        } catch (e: Exception) {
            if (e.message == null) return "unknown error"
            return e.message!!
        }
        return "setup ok"
    }

}