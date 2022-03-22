package com.zynksoftware.futureup.utils.portfolio

import android.os.Build

object PortfolioUtils {

    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model
        } else {
            "$manufacturer $model"
        }
    }
}