package com.zynksoftware.futureup.ui.wallet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zynksoftware.futureup.models.CryptoModel
import com.zynksoftware.futureup.network.Status.*
import com.zynksoftware.futureup.usecase.GetCoinsUseCase
import com.zynksoftware.futureup.usecase.GetTotalBalanceUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WalletViewModel(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase
): ViewModel() {

    val coinsLiveData = MutableLiveData<List<CryptoModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase.getCoins()
                .collect {
                    when (it.status) {
                        SUCCESS -> {
                            coinsLiveData.value = it.data!!
                        }
                        ERROR -> {
                            errorMessage.value = it.message ?: ""
                        }
                        LOADING -> {

                        }
                    }
                }
        }
    }

    fun getTotalBalance(): String {
        return getTotalBalanceUseCase.getTotalBalance().toString()
    }
}