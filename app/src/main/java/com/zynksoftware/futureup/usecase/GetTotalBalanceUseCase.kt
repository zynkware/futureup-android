package com.zynksoftware.futureup.usecase

import com.zynksoftware.futureup.Repository

class GetTotalBalanceUseCase(private val repository: Repository) {

    fun getTotalBalance(): Double {
        var balance = 0.0
        for (coin in repository.getPortfolioCoinsFromDb()) {
            balance += coin.usdValue ?: 0.toDouble()
        }
        return balance
    }
}