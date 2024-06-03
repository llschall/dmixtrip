package org.llschall.dmixtrip.model

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.util.UUID

//See https://developer.android.com/reference/android/bluetooth/BluetoothDevice
const val SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB"

class ConnectionHandler {
    companion object {
        val handler: ConnectionHandler = ConnectionHandler()
    }

    private var wrapper: Adapter? = null

    fun status(): String {
        if (wrapper == null) {
            return "no started"
        }
        return wrapper!!.status()
    }

    fun setup(context: Context): String {
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

    fun connect(): String {
        return wrapper!!.connect()
    }

    fun send() {
        return wrapper!!.send()
    }

}

private class Adapter(
    val context: Context,
    val adapter: BluetoothAdapter
) {

    val uuid = UUID.fromString(SPP_UUID)
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

        for (device in devices) {
            writer.append("# ")
            writer.append(device.name + " ")
            writer.append(device.address + "\n")
        }
        writer.append("---\n")

        socket =
            devices.first().createRfcommSocketToServiceRecord(uuid)

        adapter.cancelDiscovery()

        writer.append("Remote ${socket!!.remoteDevice.name}\n")
        writer.append("Connected ${socket!!.isConnected}\n")

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

    fun connect(): String {
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

        val name = socket!!.remoteDevice.name

        try {
            socket!!.connect()
        } catch (e: IOException) {
            return "### Connection failed on $name: " + e.message
        }
        return "Connected to $name"

    }

    fun send() {
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

        socket!!.outputStream.write(100)
        socket!!.outputStream.flush()
    }

}