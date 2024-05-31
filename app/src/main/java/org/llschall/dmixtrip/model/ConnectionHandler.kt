package org.llschall.dmixtrip.model

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ConnectionHandler {
    companion object {
        val handler: ConnectionHandler = ConnectionHandler()
    }

    var context: Context? = null

    private var wrapper: Adapter? = null

    fun status(): String {
        if (wrapper == null) {
            return "no started"
        }
        return wrapper!!.status()
    }

    fun setup(): String {
        if (context == null) return "context not found"
        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "permission not granted"
        }

        try {
            val service = ContextCompat.getSystemService(context!!, BluetoothManager::class.java)
                ?: return "service not found"
            val adapter = service.adapter ?: return "adapter not found"

            wrapper = Adapter(service, adapter)
            return "found adapter ${adapter.name} ${adapter.isEnabled}"
        } catch (e: Exception) {
            if (e.message == null) return "unknown error"
            return e.message!!
        }
    }
}

private class Adapter(val manager: BluetoothManager, val adapter: BluetoothAdapter) {

    fun status(): String {
        return """
            adapter.state ${adapter.state}
            adapter.isEnabled ${adapter.isEnabled}
            """
    }
    
}