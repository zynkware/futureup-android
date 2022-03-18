package com.zynksoftware.futureup.ui.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zynksoftware.futureup.models.CryptoModel
import com.zynksoftware.futureup.network.Status.*
import com.zynksoftware.futureup.usecase.GetCoinsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WalletViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    val coinsLiveData = MutableLiveData<List<CryptoModel>>()

    fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase.getCoins()
                .collect {
                    when (it.status) {
                        SUCCESS -> {
                            coinsLiveData.value = it.data!!
                        }
                        ERROR -> {

                        }
                        LOADING -> {

                        }
                    }
                }
        }
    }
}