package com.zynksoftware.futureup.ui.portofolio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zynksoftware.futureup.Repository
import com.zynksoftware.futureup.models.PortfolioCoinModel
import com.zynksoftware.futureup.usecase.GetTotalBalanceUseCase

class PortfolioViewModel(
    private val repository: Repository,
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase
): ViewModel() {

    val portfolioLiveData = MutableLiveData<ArrayList<PortfolioCoinModel>>()
    val portfolioBalance = MutableLiveData<String>()

    fun getPortfolioCoins() {
        portfolioLiveData.value = repository.getPortfolioCoinsFromDb()
        portfolioBalance.value = getTotalBalanceUseCase.getTotalBalance().toString()
    }
}