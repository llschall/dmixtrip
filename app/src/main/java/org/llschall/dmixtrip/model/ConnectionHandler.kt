package org.llschall.dmixtrip.model

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import java.util.UUID

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
            val manager = context!!.getSystemService(BluetoothManager::class.java)
                ?: return "manager not found"
            val adapter = manager.adapter ?: return "adapter not found"
            wrapper = Adapter(context!!, adapter)
            return "found adapter ${adapter.name} ${adapter.isEnabled}"
        } catch (e: Exception) {
            if (e.message == null) return "unknown error"
            return e.message!!
        }
    }

    fun connect(): Boolean {
        return wrapper!!.connect()
    }

    fun listen() {
        return wrapper!!.listen()
    }

}

private class Adapter(
    val context: Context,
    val adapter: BluetoothAdapter
) {

    var socket: BluetoothSocket? = null

    fun status(): String {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "permission not granted"
        }

        val devices = adapter.bondedDevices

        val writer = StringBuilder()
        writer.append("---\n")

        for (device in devices) {
            writer.append(device.name + "\n")
            writer.append(device.address + "\n")

            socket =
                device.createInsecureRfcommSocketToServiceRecord(UUID.randomUUID())

            writer.append("remote ${socket!!.remoteDevice.name}\n")
            writer.append("connected ${socket!!.isConnected}\n")

            writer.append("---\n")
        }

        writer.append(
            """
            adapter.name ${adapter.name}
            adapter.state ${adapter.state}
            adapter.isEnabled ${adapter.isEnabled}
            devices: ${devices.size}
            """
        )

        return writer.toString()
    }

    fun connect(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false
        }
        socket!!.connect()
        return socket!!.isConnected
    }

    fun listen() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            println("permission not granted")
            return
        }
        println("listening...")
        adapter.listenUsingRfcommWithServiceRecord("Galaxy", UUID.randomUUID())
        println("listening is over")
    }

}