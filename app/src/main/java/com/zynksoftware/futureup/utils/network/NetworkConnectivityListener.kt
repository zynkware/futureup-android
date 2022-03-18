package com.zynksoftware.futureup.utils.network

interface NetworkConnectivityListener {
    fun onConnected()

    fun onDisconnected()
}