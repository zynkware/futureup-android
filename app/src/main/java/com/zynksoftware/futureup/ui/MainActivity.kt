package com.zynksoftware.futureup.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.navigation.findNavController
import com.zynksoftware.futureup.R
import com.zynksoftware.futureup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    fun getViewIdToFindNavController(): Int = R.id.dashboard_nav_host_fragment

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    protected fun navigate(@IdRes actionId: Int) {
        navigate(actionId, null)
    }

    protected fun navigate(@IdRes actionId: Int, args: Bundle?) {
        if (actionId == -1) {
            Toast.makeText(
                this,
                "Navigation destination not set yet!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            findNavController(getViewIdToFindNavController()).navigate(actionId, args)
        }
    }
}