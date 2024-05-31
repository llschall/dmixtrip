package org.llschall.dmixtrip.model

import android.content.Context

class ConnectionHandler {
    companion object {
        val handler: ConnectionHandler = ConnectionHandler()
    }

    var context: Context? = null

    fun setup(): String {
        if (context == null) return "context not found"

        return "setup ok"
    }

}