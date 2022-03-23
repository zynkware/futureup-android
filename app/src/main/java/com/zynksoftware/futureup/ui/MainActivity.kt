package com.zynksoftware.futureup.ui

import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zynksoftware.futureup.databinding.ActivityMainBinding
import com.zynksoftware.futureup.utils.network.NetworkCallback
import com.zynksoftware.futureup.utils.network.NetworkConnection

class MainActivity : AppCompatActivity() {

    private val networkConnection = NetworkConnection
    private val networkCallback = object: NetworkCallback(networkConnection) {

    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerNetworkCallback()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkCallback()
    }

    private fun registerNetworkCallback() {
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
    }

    private fun unregisterNetworkCallback() {
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}