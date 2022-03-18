package com.zynksoftware.futureup.utils.network

import android.util.Log

object NetworkConnection: NetworkConnectivityListener {
    private val TAG = NetworkConnection::class.simpleName

    var isConnected: Boolean = false
        private set

    override fun onConnected() {
        updateConnectionState(true)
    }

    override fun onDisconnected() {
        updateConnectionState(false)
    }

    private fun updateConnectionState(isConnected: Boolean) {
        Log.d(TAG, "isNetworkConnected: $isConnected")
        NetworkConnection.isConnected = isConnected
    }
}