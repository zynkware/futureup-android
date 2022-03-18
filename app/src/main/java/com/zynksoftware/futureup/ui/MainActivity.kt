package com.zynksoftware.futureup.ui

import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.zynksoftware.futureup.R
import com.zynksoftware.futureup.databinding.ActivityMainBinding
import com.zynksoftware.futureup.utils.network.NetworkCallback
import com.zynksoftware.futureup.utils.network.NetworkConnection

class MainActivity : AppCompatActivity() {

    private val networkConnection = NetworkConnection
    private val networkCallback = object: NetworkCallback(networkConnection) {

    }

    fun getViewIdToFindNavController(): Int = R.id.dashboard_nav_host_fragment

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

    private fun navigate(@IdRes actionId: Int) {
        navigate(actionId, null)
    }

    private fun navigate(@IdRes actionId: Int, args: Bundle?) {
        if (actionId == -1) {
            Toast.makeText(
                this,
                "Navigation destination not set yet",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            findNavController(getViewIdToFindNavController()).navigate(actionId, args)
        }
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