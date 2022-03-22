package com.zynksoftware.futureup.utils.portfolio

import com.zynksoftware.futureup.TemporaryDB

object PortfolioUtils {

    fun getTotalBalance(): Double {
        var balance = 0.0
        for (coin in TemporaryDB.myCoins) {
            if (coin.usdValue != null) {

                balance += coin.usdValue
            } else {
                balance += 0
            }
        }
        return balance
    }
}